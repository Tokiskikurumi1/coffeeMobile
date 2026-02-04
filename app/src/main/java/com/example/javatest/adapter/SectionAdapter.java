package com.example.javatest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.SectionModel;

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

        ProductAdapter productAdapter =
                new ProductAdapter(section.getProducts());

        holder.rvHorizontal.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        );
        holder.rvHorizontal.setAdapter(productAdapter);
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
