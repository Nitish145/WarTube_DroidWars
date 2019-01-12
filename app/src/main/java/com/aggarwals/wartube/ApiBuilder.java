package com.aggarwals.wartube;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiBuilder {

    private static final String url = "https://www.googleapis.com/youtube/v3/";

    public static Statistics SubCount = null;

    public static Statistics getStatistics(){
        if (SubCount == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SubCount = retrofit.create(Statistics.class);
        }
        return SubCount;
    }


    public interface Statistics{

        @GET("channels?part=statistics")
        Call<com.aggarwals.wartube.Statistics> getSubCount (@Query("id") String id, @Query("key") String key);
    }
}
