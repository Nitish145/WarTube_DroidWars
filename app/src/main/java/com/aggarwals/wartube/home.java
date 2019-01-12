package com.aggarwals.wartube;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    TextView TseriesSub;
    TextView PewdiepieSub;
    TextView difference;

    SwipeRefreshLayout mSwipeRefreshLayout;

    PieChart pieChart;

    Long TSerSub;
    Long PewdsSub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        TseriesSubCount();
        PewdiepieSubCount();

        setUpPieChart();

        mSwipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED);

        /*Long Pewdiepiesub = Long.parseLong(PewdiepieSub.getText().toString());
        Long TSeriessub = Long.parseLong(TseriesSub.getText().toString());

        Long Difference = Pewdiepiesub-TSeriessub;

        difference = findViewById(R.id.difference);
        String text = "<font color=#cc0029>PewDiePie is currently leading T-Series by </font>" + Difference;
        difference.setText(Html.fromHtml(text));*/

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TseriesSubCount();
                PewdiepieSubCount();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



    }

    private void setUpPieChart() {

        Long[] sub = {TSerSub, PewdsSub};
        String[] channel = {"T-Series", "PewDiePie"};

        pieChart = findViewById(R.id.PieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(35);
        pieChart.setCenterText("PewDiePie v/s T-Series");
        pieChart.setCenterTextSize(8);

        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i<sub.length; i++){
            pieEntries.add(new PieEntry(sub[i], channel[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueTextSize(0);
        pieDataSet.setColor(Color.BLACK);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(178, 34, 34));
        colors.add(Color.GRAY);

        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieChart.animateY(1000);
        pieChart.setData(pieData);
        pieChart.invalidate();

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

        Call<Statistics> TseriesSubCount = ApiBuilder.getStatistics().getSubCount("UCq-Fj5jknLsUf-MWSy4_brA", "AIzaSyBveKLcR7ncGyyMIiuJAAG9XnNFtvlbaD0");
        TseriesSubCount.enqueue(new Callback<Statistics>() {
                             @Override
                             public void onResponse(Call<Statistics> call, Response<Statistics> response) {
                                 Statistics s=response.body();

                                 TseriesSub.setText(s.getItems()[0].getStatistics().getSubscriberCount());
                                 TSerSub = Long.parseLong(TseriesSub.getText().toString());

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
                                 PewdsSub = Long.parseLong(PewdiepieSub.getText().toString());

                             }

                             @Override
                             public void onFailure(Call<Statistics> call, Throwable t) {
                                 Log.i("88", "onFailure: " + t.toString());
                                 t.printStackTrace();
                             }
                         }

        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                TseriesSubCount();
                PewdiepieSubCount();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
