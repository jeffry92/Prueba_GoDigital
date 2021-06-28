package com.example.prueba_godigital.Endpoints;

import com.example.prueba_godigital.MovieInterface.MovieInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Endpoints {
    public static final String URL_BASE = "https://api.themoviedb.org/3/movie/";

    private static MovieInterface API_SERVICE;

    public static MovieInterface getApiService(){

        HttpLoggingInterceptor loggin =  new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);

        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(MovieInterface.class);
        }
        return API_SERVICE;
    }
}
