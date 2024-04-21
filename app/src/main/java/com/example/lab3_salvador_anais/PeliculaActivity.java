package com.example.lab3_salvador_anais;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.CheckBox;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.lab3_salvador_anais.databinding.ActivityMainBinding;
import com.example.lab3_salvador_anais.dto.Pelicula;
import java.util.concurrent.ExecutorService;
import com.example.lab3_salvador_anais.services.TypicodeService;

public class PeliculaActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TypicodeService typicodeService;
    private CheckBox checkBoxPelicula;
    private Button regresar;
    //textos usados
    TextView directorpeli;
    TextView actorespeli;
    TextView estrenopeli;
    TextView generopeli;
    TextView escritorespeli;
    TextView titulopelicula;
    TextView resume;
    TextView puntucion1;
    TextView puntuacion2;
    TextView totalpuntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_pelicula);

        ApplicationThreads application = (ApplicationThreads) getApplication();
        ExecutorService executorService = application.executorService;

        //checkbox
        checkBoxPelicula = findViewById(R.id.checkBoxPelicula);
        //boton
        regresar = findViewById(R.id.regresar);

        Toast.makeText(this, "Tiene internet: " + estadoConexion(), Toast.LENGTH_LONG).show();
        TypicodeService typicodeService = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TypicodeService.class);

        if(estadoConexion()){
            typicodeService.getPelicula().enqueue(new Callback<Pelicula>(){
                @Override
                public void onResponse(Call<Pelicula> call, Response<Pelicula> response){
                    if(response.isSuccessful()){
                        Pelicula pelicula = response.body();
                        Intent intent = getIntent();
                        String idpelicula = intent.getStringExtra("idpelicula");


                        TextView directorpeli = findViewById(R.id.directorpeli);
                        TextView actorespeli = findViewById(R.id.actorespeli);
                        TextView estrenopeli = findViewById(R.id.estrenopeli);
                        TextView generopeli = findViewById(R.id.generopeli);
                        TextView escritorespeli = findViewById(R.id.escritorespeli);
                        TextView titulopelicula = findViewById(R.id.titulopelicula);
                        TextView resume = findViewById(R.id.resume);
                        TextView puntucion1 = findViewById(R.id.puntucion1);
                        TextView puntuacion2 = findViewById(R.id.puntuacion2);
                        TextView totalpuntuacion = findViewById(R.id.totalpuntuacion);

                        directorpeli.setText(pelicula.getDirector());
                        actorespeli.setText(pelicula.getActors());
                        estrenopeli.setText(pelicula.getReleased());
                        generopeli.setText(pelicula.getGenre());
                        escritorespeli.setText(pelicula.getWriter());
                        titulopelicula.setText(pelicula.getTitle());
                        resume.setText(pelicula.getPlot());

                    }
                }


                @Override
                public void onFailure(Call<Pelicula> call, Throwable t) {
                }


            });
        }

        /* checkBox el cual habilite un botón para regresar en la aplicación
        hacia el menú Principal*/
        regresar.setEnabled(false); //boton
        checkBoxPelicula.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                regresar.setEnabled(isChecked);
                updateButtonColor();
            }
        });
        checkBoxPelicula.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                regresar.setEnabled(checkBoxPelicula.isChecked());
            }
        });


    }
    private void updateButtonColor(){
        if (regresar.isEnabled()) {
            regresar.setBackgroundColor(Color.parseColor("#FE9700"));
        } else {
            regresar.setBackgroundColor(Color.GRAY);
        }
    }


    public boolean estadoConexion() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean estadoConexion = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test", "Internet: " + estadoConexion);

        return estadoConexion;
    }
    //regresar boton
    public void regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
