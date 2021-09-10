package io.app.asico;

import java.util.List;

import io.app.asico.model.Region;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("asia")
    Call<List<Region>> getRegions();
}
