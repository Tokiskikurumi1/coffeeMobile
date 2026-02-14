package com.example.javatest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.Product;
import com.example.javatest.product.ProductDetailFragment;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    private Context context;
    private List<String> categories;
    private List<Product> allProducts;

    public SectionAdapter(Context context, List<String> categories, List<Product> allProducts) {
        this.context = context;
        this.categories = categories;
        this.allProducts = allProducts;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_section, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {

        String category = categories.get(position);
        holder.txtTitle.setText(category);

        List<Product> filtered = new ArrayList<>();

        for (Product p : allProducts) {
            if (p.getCategory().equals(category)) {
                filtered.add(p);
            }
        }

        ProductAdapter adapter = new ProductAdapter(context, filtered, product -> {

            Bundle bundle = new Bundle();
            bundle.putString("name", product.getName());
            bundle.putInt("price", product.getPrice());
            bundle.putInt("image", product.getImageResId());

            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(bundle);

            FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();

            fm.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        holder.rvHorizontal.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        );

        holder.rvHorizontal.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        RecyclerView rvHorizontal;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            rvHorizontal = itemView.findViewById(R.id.rvHorizontal);
        }
    }
}
