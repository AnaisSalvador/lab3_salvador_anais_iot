package com.example.lab3_salvador_anais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import android.os.Bundle;
import android.widget.Button;

import com.example.lab3_salvador_anais.worker.ContadorWorker;
public class ContadorActivity0 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        //Button button  = findViewById(R.id.botonprimopr);
        //button.setOnClickListener(view -> {

         //   WorkRequest workRequest = new OneTimeWorkRequest.Builder(ContadorWorker.class).build();

        //    WorkManager
        //            .getInstance(ContadorActivity0.this.getApplicationContext())
        //            .enqueue(workRequest);
        //});
    }
}
