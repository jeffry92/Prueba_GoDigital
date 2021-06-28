package com.example.prueba_godigital.MovieInterface;

import com.example.prueba_godigital.ObjetArrayLlist.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {

    String API_ROUT="popular?api_key=45844491d0d17546d56506d1dc7a28c5&page=";

    @GET(API_ROUT)
    Call<Movie> getMovies(@Query("page") String idPage);


}
