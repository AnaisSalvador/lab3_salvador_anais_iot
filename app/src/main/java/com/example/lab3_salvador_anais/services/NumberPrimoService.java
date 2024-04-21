package com.example.lab3_salvador_anais.services;
import com.example.lab3_salvador_anais.dto.Impares;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NumberPrimoService {

        @GET("/typicode/demo/impares")
        Call<List<Impares>> getImpares();

        @GET("/primeNumbers")
        Call<Impares> getPrimeNumber(@Query("len") int len,
                                     @Query("order") int order);

}
