package com.example.lab3_salvador_anais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.lab3_salvador_anais.databinding.ActivityMainBinding;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.lab3_salvador_anais.dto.Pelicula;
import java.util.concurrent.ExecutorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MAINACTDEBUG";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void visualizarprimo(View view) {

        Intent intent = new Intent(this, ContadorActivity.class);
        startActivity(intent);

    }

    public void buscarpelicula(View view) {
        //buscar por id y luego obtener el texto
        EditText editText = findViewById(R.id.idpelicula);
        String idpelicula = editText.getText().toString();
        Intent intent = new Intent(this, PeliculaActivity.class);
        intent.putExtra("idpelicula",idpelicula);
        startActivity(intent);

    }


}