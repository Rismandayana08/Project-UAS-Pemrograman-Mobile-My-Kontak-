package com.example.kontakapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Contact c = (Contact) getIntent().getSerializableExtra("contact");
        if (c == null) {
            finish();
            return;
        }

        TextView tvName   = findViewById(R.id.tv_detail_name);
        TextView tvPhone  = findViewById(R.id.tv_phone);
        TextView tvEmail  = findViewById(R.id.tv_email);
        TextView tvAddress= findViewById(R.id.tv_address);
        TextView tvNotes  = findViewById(R.id.tv_notes);

        tvName.setText(c.getName());
        tvPhone.setText(c.getPhone());
        tvEmail.setText(c.getEmail());
        tvAddress.setText(c.getAddress());
        tvNotes.setText(c.getNotes());
    }
}