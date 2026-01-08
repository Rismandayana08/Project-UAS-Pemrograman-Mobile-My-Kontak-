package com.example.kontakapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay 2 detik, lalu pindah ke LoadingActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoadingActivity.class));
            finish(); // biar tidak bisa kembali ke splash dengan tombol back
        }, 2000); // 2000 = 2 detik
    }
}