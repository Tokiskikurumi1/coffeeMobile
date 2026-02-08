package com.example.javatest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.Revenue;

import java.util.ArrayList;

public class RevenueAdapter extends RecyclerView.Adapter<RevenueAdapter.ViewHolder> {

    ArrayList<Revenue> list;

    public RevenueAdapter(ArrayList<Revenue> list) {
        this.list = list;
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

        holder.txtMaHD.setText("Mã HĐ: " + revenue.getMaHD());
        holder.txtNgay.setText("Ngày tạo: " + revenue.getNgayTao());
        holder.txtTien.setText(String.format("%,.0f VND", revenue.getTongTien()));
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
