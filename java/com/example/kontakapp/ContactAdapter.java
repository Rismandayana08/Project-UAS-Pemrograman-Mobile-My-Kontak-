package com.example.kontakapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.VH> {

    private final ArrayList<Contact> data;
    private final OnClickListener clickListener;
    private final OnLongClickListener longClickListener;

    // Interface untuk klik biasa (buka detail)
    public interface OnClickListener {
        void onClick(Contact contact);
    }

    // Interface untuk klik lama (Edit/Hapus)
    public interface OnLongClickListener {
        void onLongClick(Contact contact, int position);
    }

    // Constructor (dipanggil dari ContactListActivity)
    public ContactAdapter(ArrayList<Contact> data,
                          OnClickListener clickListener,
                          OnLongClickListener longClickListener) {
        this.data = data;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new VH(v);
    }

    // INI DIA YANG KAMU CARI: onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Contact c = data.get(position);
        holder.tvName.setText(c.getName());
        holder.tvPhone.setText(c.getPhone());

        // Klik biasa → buka detail
        holder.itemView.setOnClickListener(v -> clickListener.onClick(c));

        // KLIK LAMA → muncul dialog Edit/Hapus
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onLongClick(c, holder.getAdapterPosition());
            return true; // artinya event sudah ditangani
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // ViewHolder
    static class VH extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;

        VH(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
        }
    }
}