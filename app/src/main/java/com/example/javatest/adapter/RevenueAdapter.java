package com.example.javatest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.Revenue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RevenueAdapter extends RecyclerView.Adapter<RevenueAdapter.ViewHolder> {

    ArrayList<Revenue> list;
    OnBillClick listener;

    public interface OnBillClick{
        void onClick(int idBill);
    }

    public RevenueAdapter(ArrayList<Revenue> list, OnBillClick l) {
        this.list = list;
        this.listener = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_revenue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Revenue revenue = list.get(position);

        holder.txtMaHD.setText("Mã HĐ: " + revenue.getIdBill());

        try {
            // 🔥 convert chuỗi số -> long
            long time = Long.parseLong(revenue.getDate());

            Date date = new Date(time);

            SimpleDateFormat showFormat =
                    new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

            holder.txtNgay.setText("Ngày: " + showFormat.format(date));

        } catch (Exception e) {
            holder.txtNgay.setText(revenue.getDate());
        }
        holder.txtTien.setText(String.format("%,.0f VND", revenue.getTotal()));

        holder.itemView.setOnClickListener(v-> listener.onClick(revenue.getIdBill()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMaHD, txtNgay, txtTien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaHD = itemView.findViewById(R.id.txtMaHD);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTien = itemView.findViewById(R.id.txtTien);
        }
    }
}