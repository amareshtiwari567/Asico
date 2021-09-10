package io.app.asico;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import io.app.asico.databinding.ActivityMainBinding;
import io.app.asico.model.Region;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<Region> regions;
    private ActivityMainBinding binding;

    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.listRegions.setLayoutManager(new LinearLayoutManager((this)));

        apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Region>> call=apiInterface.getRegions();
        call.enqueue(new Callback<List<Region>>() {
            @Override
            public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                Log.d("TAG", "com");
                regions = response.body();
                binding.listRegions.setAdapter(new RegionsListAdapter(regions));

               // AppDatabase appDatabase= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"asia").build();
              //  RoomDAO roomDAO= appDatabase.asiaDao();
             //   roomDAO.insertAsiaDetail(regions);
            }

            @Override
            public void onFailure(Call<List<Region>> call, Throwable t) {
                Log.d(TAG, "onFailure: Something went very fucking wrong fuck you");
            }
        });
    }

}