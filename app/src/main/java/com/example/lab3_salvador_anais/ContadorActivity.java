package com.example.lab3_salvador_anais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.lab3_salvador_anais.databinding.ActivityContadorBinding;
import com.example.lab3_salvador_anais.dto.Impares;
import com.example.lab3_salvador_anais.services.NumberPrimoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContadorActivity extends AppCompatActivity {
    private ActivityContadorBinding binding;
    private TextView primocontador;
    int contador = 1;
    NumberPrimoService numberPrimoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityContadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botonprimopr.setOnClickListener(view -> {
            while(contador <= 10){
                binding.primocontador.setText(String.valueOf(contador));
                contador ++;

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        });

        /* Internet */

        Toast.makeText(this, "Tiene internet: " + estadoConexion(), Toast.LENGTH_LONG).show();


        // Utilizando Retrofit
        NumberPrimoService numberPrimoService = new Retrofit.Builder()
                .baseUrl("https://prime-number-api.onrender.com/primeNumbers?len=999&order=1")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NumberPrimoService.class);

        //binding.botonprimopr.setOnClickListener(view -> fetchProfileFromWs());

    }

    public void fetchImparesFromWs(){
        if(estadoConexion()){
            numberPrimoService.getImpares().enqueue(new Callback<List<Impares>>() {
                @Override
                public void onResponse(Call<List<Impares>> call, Response<List<Impares>> response) {
                    //aca estoy en el UI Thread
                    if(response.isSuccessful()){
                        List<Impares> impares = response.body();
                        for(Impares i : impares){
                            Log.d("msg-test-ws-comments","Number: "
                                    + i.getNumber() + " | Order: " + i.getOrder());
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Impares>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }


    public boolean estadoConexion() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean estadoConexion = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test", "Internet: " + estadoConexion);

        return estadoConexion;
    }

}
