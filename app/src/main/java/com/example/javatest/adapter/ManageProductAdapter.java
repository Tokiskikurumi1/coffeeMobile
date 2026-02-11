package com.example.javatest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.Product;

import java.util.ArrayList;

public class ManageProductAdapter extends RecyclerView.Adapter<ManageProductAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> list;

    public ManageProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_manage_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = list.get(position);

        holder.txtName.setText(product.getName());
        holder.txtPrice.setText(product.getPrice() + " VNĐ");

        holder.btnEdit.setOnClickListener(v ->
                Toast.makeText(context, "Sửa: " + product.getName(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPrice;
        Button btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
