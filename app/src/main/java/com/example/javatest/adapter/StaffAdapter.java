package com.example.javatest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.account.StaffDetailActivity;
import com.example.javatest.model.Staff;
import android.widget.TextView;

import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {

    Context context;
    ArrayList<Staff> list;

    public StaffAdapter(Context context, ArrayList<Staff> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_staff, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Staff staff = list.get(position);

        holder.txtId.setText(staff.getId());
        holder.txtName.setText(staff.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StaffDetailActivity.class);
            intent.putExtra("id", staff.getId());
            intent.putExtra("name", staff.getName());
            intent.putExtra("birth", staff.getBirth());
            intent.putExtra("gender", staff.getGender());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
