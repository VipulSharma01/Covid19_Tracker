package com.covid.covidtracker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.covid.covidtracker.api.ApiUtilites;
import com.covid.covidtracker.api.CountryData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView cname,date,total_confirm,toady_confirm,total_active,total_recovered,today_recovered,total_death,toady_death,total_tests;
    private List<CountryData> list;
    private PieChart pieChart;

    String country = "India";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        if (getIntent().getStringExtra("country")!=null){
            country = getIntent().getStringExtra("country");
        }


        init();

        cname.setText(country);
        cname.setOnClickListener(v->
                startActivity(new Intent(MainActivity.this,CountryActivity.class)));
        ApiUtilites.getApiInterface().getCountryData().enqueue(new Callback<List<CountryData>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {

                list.addAll(response.body());

                for (int i =0;i<list.size();i++){
                    if (list.get(i).getCountry().equals(country)){
                        int confirm =Integer.parseInt(list.get(i).getCases());
                        int active =Integer.parseInt(list.get(i).getActive());
                        int death =Integer.parseInt(list.get(i).getDeaths());
                        int recovered =Integer.parseInt(list.get(i).getRecovered());

                        total_confirm.setText(NumberFormat.getInstance().format(confirm));
                        total_active.setText(NumberFormat.getInstance().format(active));
                        total_recovered.setText(NumberFormat.getInstance().format(recovered));
                        total_death.setText(NumberFormat.getInstance().format(death));
                        toady_death.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                        toady_confirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                        today_recovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                        total_tests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                        setText(list.get(i).getUpdated());

                        pieChart.addPieSlice(new PieModel("Confirm",confirm,getResources().getColor(R.color.yellow)));
                        pieChart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.blue_pie)));
                        pieChart.addPieSlice(new PieModel("Recovered",recovered,getResources().getColor(R.color.green_pie)));
                        pieChart.addPieSlice(new PieModel("Death",death,getResources().getColor(R.color.red_pie)));
                        pieChart.startAnimation();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CountryData>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setText(String updated) {
        @SuppressLint("SimpleDateFormat")
        DateFormat format = new SimpleDateFormat("MMM dd,yyyy");
        long millisecond = Long.parseLong(updated);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        date.setText("Updated at "+format.format(calendar.getTime()));
    }

    private void init(){

        toady_confirm = findViewById(R.id.today_confirm);
        total_confirm =findViewById(R.id.total_confirm);
        total_death = findViewById(R.id.total_death);
        toady_death = findViewById(R.id.today_death);
        total_active = findViewById(R.id.total_active);
        today_recovered = findViewById(R.id.today_recovered);
        total_recovered = findViewById(R.id.total_recovered);
        total_tests = findViewById(R.id.total_test);
        pieChart = findViewById(R.id.piechart);
        date = findViewById(R.id.date);
        cname = findViewById(R.id.cname);

    }
}