package com.example.kontakapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Tunggu 3 detik lalu pindah ke PermissionActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(LoadingActivity.this, PermissionActivity.class));
            finish();
        }, 3000); // 3000 = 3 detik
    }
}