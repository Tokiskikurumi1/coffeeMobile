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
import com.example.javatest.model.SectionModel;
import com.example.javatest.product.ProductDetailFragment;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    private Context context;
    private List<SectionModel> sections;

    public SectionAdapter(Context context, List<SectionModel> sections) {
        this.context = context;
        this.sections = sections;
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

        SectionModel section = sections.get(position);
        holder.txtTitle.setText(section.getTitle());

        // 🔥 IMPORTANT — chỉ set adapter 1 lần
        if (holder.rvHorizontal.getAdapter() == null) {

            ProductAdapter adapter = new ProductAdapter(
                    context,
                    new ArrayList<>(section.getProducts()), // clone list
                    product -> {

                        Bundle bundle = new Bundle();
                        bundle.putInt("id", product.getIdFood());
                        bundle.putString("name", product.getNameFood());
                        bundle.putDouble("price", product.getPrice());
                        bundle.putString("image", product.getImage());
                        bundle.putInt("cate", product.getIdCate());

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
    }

    @Override
    public int getItemCount() {
        return sections.size();
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