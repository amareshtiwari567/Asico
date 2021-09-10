package io.app.asico;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


        public static Retrofit getRetrofit() {

                //Init of retrofit method
                final String BASE_URL = "https://restcountries.eu/rest/v2/region/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                return retrofit;
        }

}
