package com.example.javatest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private List<String> list;

    public ProductListAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_product_list, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.txtName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView txtName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
