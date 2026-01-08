package com.example.kontakapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddContactActivity extends AppCompatActivity {

    private Contact editContact = null;
    private int editPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        TextInputEditText etName = findViewById(R.id.et_name);
        TextInputEditText etPhone = findViewById(R.id.et_phone);
        TextInputEditText etEmail = findViewById(R.id.et_email);
        TextInputEditText etAddress = findViewById(R.id.et_address);
        TextInputEditText etNotes = findViewById(R.id.et_notes);
        Button btnSave = findViewById(R.id.btn_save);

        // Cek apakah mode edit
        if (getIntent().hasExtra("edit_contact")) {
            editContact = (Contact) getIntent().getSerializableExtra("edit_contact");
            editPosition = getIntent().getIntExtra("edit_position", -1);

            etName.setText(editContact.getName());
            etPhone.setText(editContact.getPhone());
            etEmail.setText(editContact.getEmail());
            etAddress.setText(editContact.getAddress());
            etNotes.setText(editContact.getNotes());

            setTitle("Edit Kontak");
            btnSave.setText("UPDATE");
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String notes = etNotes.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                if (name.isEmpty()) etName.setError("Wajib diisi");
                if (phone.isEmpty()) etPhone.setError("Wajib diisi");
                return;
            }

            Contact resultContact = new Contact(name, phone, email, address, notes);

            Intent result = new Intent();
            if (editContact != null) {
                result.putExtra("edited_contact", resultContact);
                result.putExtra("edit_position", editPosition);
            } else {
                result.putExtra("new_contact", resultContact);
            }
            setResult(RESULT_OK, result);
            finish();
        });
    }
}