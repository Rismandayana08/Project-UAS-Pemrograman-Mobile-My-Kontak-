package com.example.kontakapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ContactListActivity extends AppCompatActivity {  // atau MainActivity

    private static final int LOCATION_PERMISSION_CODE = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView tvReadingLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);  // atau activity_main

        tvReadingLocation = findViewById(R.id.tv_reading_location);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Otomatis ambil lokasi saat app dibuka
        getCurrentLocation();
    }

    private void getCurrentLocation() {
        tvReadingLocation.setText("Mencari lokasi...");

        // Cek permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_CODE);
            return;
        }

        // Ambil lokasi terakhir
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        convertToCityName(location.getLatitude(), location.getLongitude());
                    } else {
                        tvReadingLocation.setText("Lokasi tidak tersedia");
                    }
                })
                .addOnFailureListener(e -> {
                    tvReadingLocation.setText("Gagal ambil lokasi");
                });
    }

    private void convertToCityName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);

                // Prioritas: Kecamatan → Kota → Kabupaten → Provinsi
                String locationName = address.getSubLocality();  // Kecamatan
                if (locationName == null) locationName = address.getLocality();  // Kota
                if (locationName == null) locationName = address.getSubAdminArea();  // Kabupaten
                if (locationName == null) locationName = address.getAdminArea();  // Provinsi

                if (locationName != null) {
                    tvReadingLocation.setText("Membaca di " + locationName);
                } else {
                    tvReadingLocation.setText("Membaca di Indonesia");
                }
            } else {
                tvReadingLocation.setText("Membaca di Indonesia");
            }
        } catch (IOException e) {
            tvReadingLocation.setText("Koneksi internet diperlukan");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                getCurrentLocation();
            } else {
                tvReadingLocation.setText("Izin lokasi ditolak");
            }
        }
    }
}