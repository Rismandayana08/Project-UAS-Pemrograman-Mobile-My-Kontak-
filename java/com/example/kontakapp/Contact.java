package com.example.kontakapp;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name, phone, email, address, notes;

    public Contact(String name, String phone, String email, String address, String notes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.notes = notes;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getNotes() { return notes; }
}