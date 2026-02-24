package com.example.javatest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.model.BillDetailItem;

import java.util.List;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.VH> {

    List<BillDetailItem> list;

    public BillDetailAdapter(List<BillDetailItem> l){
        list = l;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int v){
        View view = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_bill_detail,p,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder,int position){

        BillDetailItem item = list.get(position);

        holder.txtName.setText(item.getName());
        holder.txtQty.setText("x" + item.getQty());
        holder.txtPrice.setText(String.valueOf(item.getTotal()));
    }

    @Override
    public int getItemCount(){return list.size();}

    public class VH extends RecyclerView.ViewHolder {

        TextView txtName, txtQty, txtPrice;

        public VH(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}