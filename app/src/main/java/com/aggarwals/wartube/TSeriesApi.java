package com.aggarwals.wartube;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TSeriesApi {

    private static final String key = "AIzaSyBveKLcR7ncGyyMIiuJAAG9XnNFtvlbaD0";
    private static final String url = "https://www.googleapis.com/youtube/v3/";

    public static Statistics tSeriesSub = null;

    public static Statistics getStatistics(){
        if (tSeriesSub == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            tSeriesSub = retrofit.create(Statistics.class);
        }
        return tSeriesSub;
    }


    public interface Statistics{

        @GET("channels?part=statistics")
        Call<com.aggarwals.wartube.Statistics> getSubCount (@Query("id") String id, @Query("key") String key);
    }
}
