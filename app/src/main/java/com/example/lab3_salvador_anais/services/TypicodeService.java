package com.example.lab3_salvador_anais.services;

import com.example.lab3_salvador_anais.dto.Pelicula;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypicodeService {
    @GET("/?apikey=bf81d461&i=tt3896198")
    Call<Pelicula> getPelicula();
}
