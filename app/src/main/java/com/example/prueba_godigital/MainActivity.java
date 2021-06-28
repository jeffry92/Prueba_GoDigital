package com.example.prueba_godigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.prueba_godigital.Endpoints.Endpoints;
import com.example.prueba_godigital.ObjetArrayLlist.Movie;
import com.example.prueba_godigital.ObjetArrayLlist.Results;
import com.example.prueba_godigital.adapters.AdapterMovie;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    AdapterMovie adapterMovie;
    RecyclerView recyclerView;
    ArrayList<Results> results;
    Button btnBack;
    Button btnNext;
    ProgressBar progressBar;
    LinearLayout linebtn;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progres);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        linebtn = findViewById(R.id.linearBtn);

        btnBack.setOnClickListener(onClickListener);
        btnNext.setOnClickListener(onClickListener);
        metodoprueba(i);
    }

    private View.OnClickListener onClickListener = v -> {
        int viewClic = v.getId();
        switch (viewClic){
            case R.id.btnBack:
                if (i == 0)
                    i=1;
                else
                    i=i-1;
                progressBar.setVisibility(View.VISIBLE);
                metodoprueba(i);
                break;
            case R.id.btnNext:
                if (i <= 928)
                    progressBar.setVisibility(View.VISIBLE);
                    metodoprueba(i = i + 1);
                break;
            default:
                i=1;
                break;
        }
    };

    private void metodoprueba(int s) {
        Call<Movie> call = Endpoints.getApiService().getMovies(s);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movies = response.body();
                    if (movies != null) {
                        List<Results> list = movies.getResults();
                        int page = movies.getTotal_pages();
                        if (list.size() > 0) {
                            results = new ArrayList<Results>(list);

                        }
                    }
                    recyclerView.setVisibility(View.VISIBLE);
                    linebtn.setVisibility(View.VISIBLE);
                    adapterload(results);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("cla", "onFailure: " + t.getMessage());
            }
        });


    }

    private void adapterload(ArrayList<Results> results) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        adapterMovie = new AdapterMovie(results, this);
        recyclerView.setAdapter(adapterMovie);
    }
}