package io.app.asico;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.app.asico.databinding.ActivityMainBinding;
import io.app.asico.model.Language;
import io.app.asico.model.Region;
import io.app.asico.room.AppDatabase;
import io.app.asico.room.RoomDAO;
import io.app.asico.room.RoomEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<Region> regions = new ArrayList<>();
    private ActivityMainBinding binding;
    AppDatabase appDatabase;
    RoomDAO roomDAO;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.listRegions.setLayoutManager(new LinearLayoutManager((this)));
        appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"asia")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        roomDAO = appDatabase.asiaDao();

        apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Region>> call=apiInterface.getRegions();
        call.enqueue(new Callback<List<Region>>() {
            @Override
            public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                Log.d("TAG", "Data fetch complete");
                regions = response.body();
                binding.listRegions.setAdapter(new RegionsListAdapter(MainActivity.this, regions));

                List<RoomEntity> roomEntities = new ArrayList<>(regions.size());
                roomDAO.nuke();

                for (Region region : regions) {
                    roomEntities.add(new RoomEntity(
                            region.getName(),
                            region.getCapital(),
                            region.getFlag(),
                            region.getRegion(),
                            region.getSubregion(),
                            region.getPopulation(),
                            bordersToString(region.getBorders()),
                            languagesToString(region.getLanguages())
                    ));
                }

                roomDAO.insertAsiaDetail(roomEntities);
            }

            @Override
            public void onFailure(Call<List<Region>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                List<RoomEntity> roomEntities = roomDAO.getRegions();

                for (RoomEntity entity : roomEntities) {
                    regions.add(new Region(
                            entity.getName(),
                            entity.getCapital(),
                            entity.getRegion(),
                            entity.getSubregion(),
                            entity.getPopulation(),
                            stringToBorders(entity.getBorders()),
                            stringToLanguages(entity.getLanguages()),
                                    entity.getFlag()
                    ));
                }
                binding.listRegions.setAdapter(new RegionsListAdapter(MainActivity.this, regions));
            }
        });
    }

    private String bordersToString(List<String> borders) {
        Gson gson = new Gson();
        String json = gson.toJson(borders);
        return json;
    }

    private String languagesToString(List<Language> languages) {
        Gson gson = new Gson();
        String json = gson.toJson(languages);
        return json;
    }

    private List<String> stringToBorders(String borders) {
        return new Gson().fromJson(borders, new TypeToken<List<String>>(){}.getType());
    }

    private List<Language> stringToLanguages(String languages) {
        return new Gson().fromJson(languages, new TypeToken<List<Language>>(){}.getType());
    }
}