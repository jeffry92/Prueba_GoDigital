package com.example.prueba_godigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.prueba_godigital.Endpoints.Endpoints;
import com.example.prueba_godigital.ObjetArrayLlist.Movie;
import com.example.prueba_godigital.pruebasqlite.Conexionsqlite;
import com.example.prueba_godigital.pruebasqlite.FeedEntry;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Conexionsqlite conn;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       metodoprueba();
    }

    private void metodoprueba() {
       Call<Movie> call = Endpoints.getApiService().getMovies("2");
       call.enqueue(new Callback<Movie>() {
           @Override
           public void onResponse(Call<Movie> call, Response<Movie> response) {
              if (response.isSuccessful()){
                  conn = new Conexionsqlite(MainActivity.this, "bd_prueba", null,1);
                  db = conn.getWritableDatabase();
                  ContentValues values = new ContentValues();
                  Gson gson = new Gson();
                  Movie movies = response.body();
                  values.put(FeedEntry.DATA, gson.toJson(movies));
                  db.insert(FeedEntry.TABLE_MOVIES, null, values);
              }
           }

           @Override
           public void onFailure(Call<Movie> call, Throwable t) {
               Log.e("cla", "onFailure: " + t.getMessage());
           }
       });


    }
}