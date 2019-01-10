package com.aggarwals.wartube;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    TextView TseriesSub;
    TextView PewdiepieSub;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        TseriesSubCount();
        PewdiepieSubCount();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.posts_notifications:
                Intent intent4 = new Intent(home.this, posts.class);
                startActivity(intent4);
                break;
        }
        return false;
    }

    private void TseriesSubCount() {

        TseriesSub = findViewById(R.id.TseriesSub);
        PewdiepieSub = findViewById(R.id.PewdiepieSub);

        Call<Statistics> TseriesSubCount = ApiBuilder.getStatistics().getSubCount("UCq-Fj5jknLsUf-MWSy4_brA", "AIzaSyBveKLcR7ncGyyMIiuJAAG9XnNFtvlbaD0");
        TseriesSubCount.enqueue(new Callback<Statistics>() {
                             @Override
                             public void onResponse(Call<Statistics> call, Response<Statistics> response) {
                                 Statistics s=response.body();

                                 TseriesSub.setText(s.getItems()[0].getStatistics().getSubscriberCount());

                             }

                             @Override
                             public void onFailure(Call<Statistics> call, Throwable t) {
                                 Log.i("89", "onFailure: " + t.toString());
                                 t.printStackTrace();
                             }
                         }

        );

    }

    private void PewdiepieSubCount() {

        PewdiepieSub = findViewById(R.id.PewdiepieSub);

        Call<Statistics> PewdiepieSubCount = ApiBuilder.getStatistics().getSubCount("UC-lHJZR3Gqxm24_Vd_AJ5Yw", "AIzaSyBveKLcR7ncGyyMIiuJAAG9XnNFtvlbaD0");
        PewdiepieSubCount.enqueue(new Callback<Statistics>() {
                             @Override
                             public void onResponse(Call<Statistics> call, Response<Statistics> response) {
                                 Statistics s=response.body();

                                 PewdiepieSub.setText(s.getItems()[0].getStatistics().getSubscriberCount());

                             }

                             @Override
                             public void onFailure(Call<Statistics> call, Throwable t) {
                                 Log.i("88", "onFailure: " + t.toString());
                                 t.printStackTrace();
                             }
                         }

        );

    }

}
