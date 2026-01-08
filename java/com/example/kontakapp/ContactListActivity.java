package com.example.kontakapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private ContactAdapter adapter;
    private static final int REQUEST_ADD = 100;
    private static final int REQUEST_EDIT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        dummyData();

        RecyclerView rv = findViewById(R.id.rv_contacts);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // ADAPTER DENGAN KLIK BIASA + KLIK LAMA (INI YANG PENTING!)
        adapter = new ContactAdapter(
                contacts,
                // Klik biasa → buka detail
                contact -> {
                    Intent i = new Intent(ContactListActivity.this, ContactDetailActivity.class);
                    i.putExtra("contact", contact);
                    startActivity(i);
                },
                // Klik lama → muncul dialog Edit / Hapus
                (contact, position) -> showEditDeleteDialog(contact, position)
        );

        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v ->
                startActivityForResult(new Intent(this, AddContactActivity.class), REQUEST_ADD)
        );
    }

    private void dummyData() {
        contacts.add(new Contact("ALAN", "+6283-XXX-XXX", "alancyu@gmail.com",
                "Jl.manaaja No.100, konoha", "Mie Ayam"));
        contacts.add(new Contact("BADRUUL", "+6283-XXX-XXX", "badrul@gmail.com",
                "Jl.manaaja No.100, konoha", "Tukang Galon"));
    }

    private void showEditDeleteDialog(Contact contact, int position) {
        new AlertDialog.Builder(this)
                .setTitle(contact.getName())
                .setItems(new String[]{"Edit Kontak", "Hapus Kontak"}, (dialog, which) -> {
                    if (which == 0) {
                        // EDIT KONTAK
                        Intent i = new Intent(this, AddContactActivity.class);
                        i.putExtra("edit_contact", contact);
                        i.putExtra("edit_position", position);
                        startActivityForResult(i, REQUEST_EDIT);
                    } else {
                        // HAPUS KONTAK
                        new AlertDialog.Builder(this)
                                .setTitle("Hapus Kontak")
                                .setMessage("Yakin ingin menghapus " + contact.getName() + "?")
                                .setPositiveButton("Hapus", (d, w) -> {
                                    contacts.remove(position);
                                    adapter.notifyItemRemoved(position);
                                    adapter.notifyItemRangeChanged(position, contacts.size());
                                })
                                .setNegativeButton("Batal", null)
                                .show();
                    }
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_ADD) {
                Contact baru = (Contact) data.getSerializableExtra("new_contact");
                if (baru != null) {
                    contacts.add(baru);
                    adapter.notifyItemInserted(contacts.size() - 1);
                    RecyclerView rv = findViewById(R.id.rv_contacts);
                    rv.scrollToPosition(contacts.size() - 1);
                }
            }
            else if (requestCode == REQUEST_EDIT) {
                Contact edited = (Contact) data.getSerializableExtra("edited_contact");
                int pos = data.getIntExtra("edit_position", -1);
                if (edited != null && pos != -1) {
                    contacts.set(pos, edited);
                    adapter.notifyItemChanged(pos);
                    RecyclerView rv = findViewById(R.id.rv_contacts);
                    rv.scrollToPosition(pos);
                }
            }
        }
    }
}