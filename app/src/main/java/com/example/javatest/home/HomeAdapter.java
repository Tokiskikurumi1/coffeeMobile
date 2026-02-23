package com.example.javatest.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.Product;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    List<Product> list;
    OnProductClick listener;

    public interface OnProductClick {
        void onClick(Product p);
    }

    public HomeAdapter(Context context, List<Product> list, OnProductClick listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_home, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {

        Product p = list.get(position);

        h.txtName.setText(p.getNameFood());

        int imgId = context.getResources()
                .getIdentifier(p.getImage(),"drawable",context.getPackageName());

        h.img.setImageResource(imgId);

        h.itemView.setOnClickListener(v -> listener.onClick(p));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}