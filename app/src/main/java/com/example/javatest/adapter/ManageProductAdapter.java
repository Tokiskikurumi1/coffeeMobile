package com.example.javatest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.Product;

import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ManageProductAdapter extends RecyclerView.Adapter<ManageProductAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> list;
    OnAction action;

    // ✅ interface callback
    public interface OnAction{
        void onEdit(Product p);
        void onDelete(Product p);
    }

    // ✅ constructor đúng
    public ManageProductAdapter(Context context, ArrayList<Product> list, OnAction action) {
        this.context = context;
        this.list = list;
        this.action = action;
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

        holder.txtName.setText(product.getNameFood());
        holder.txtPrice.setText(product.getPrice()+" VNĐ");

        holder.btnEdit.setOnClickListener(v -> {
            if(action!=null) action.onEdit(product);
        });

        holder.btnDelete.setOnClickListener(v -> {
            if(action!=null) action.onDelete(product);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtPrice;
        ImageButton btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
    }
}