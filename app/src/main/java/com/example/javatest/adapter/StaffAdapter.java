package com.example.javatest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.account.StaffDetailActivity;
import com.example.javatest.dao.StaffDAO;
import com.example.javatest.model.User;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {

    Context context;
    List<User> list;
    StaffDAO dao;

    public StaffAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
        dao = new StaffDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_staff, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = list.get(position);

        holder.txtName.setText(user.getName());
        holder.txtUserName.setText(user.getUserName());
        holder.txtId.setText("ID: " + user.getIdUser());

        // DELETE
        holder.btnDelete.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Xóa nhân viên")
                    .setMessage("Bạn có chắc muốn xóa?")
                    .setPositiveButton("Có", (dialog, which) -> {

                        int pos = holder.getAdapterPosition();

                        if (pos == RecyclerView.NO_POSITION) return;

                        if (dao.deleteStaff(user.getIdUser())) {
                            list.remove(pos);
                            notifyItemRemoved(pos);
                        }

                    })
                    .setNegativeButton("Không", null)
                    .show();
        });

        holder.btnEdit.setOnClickListener(v -> {

            Intent i = new Intent(context, StaffDetailActivity.class);

            i.putExtra("id", user.getIdUser());
            i.putExtra("name", user.getName());
            i.putExtra("dob", user.getDob());
            i.putExtra("gender", user.getGender());

            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ===== VIEW HOLDER =====
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtUserName, txtId;
        ImageView btnDelete, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtId = itemView.findViewById(R.id.txtId);

            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}