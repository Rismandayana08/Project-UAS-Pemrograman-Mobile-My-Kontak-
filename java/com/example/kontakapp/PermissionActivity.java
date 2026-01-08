package com.example.kontakapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        Button btnWhileUsing = findViewById(R.id.btn_while_using);
        Button btnOnlyThisTime = findViewById(R.id.btn_only_this_time);
        Button btnDontAllow = findViewById(R.id.btn_dont_allow);

        // Ketiga tombol langsung lanjut ke daftar kontak (sesuai screenshot asli)
        btnWhileUsing.setOnClickListener(v -> openContactList());
        btnOnlyThisTime.setOnClickListener(v -> openContactList());
        btnDontAllow.setOnClickListener(v -> openContactList());
    }

    private void openContactList() {
        startActivity(new Intent(this, ContactListActivity.class));
        finish();
    }
}