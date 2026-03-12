package com.example.javatest.account;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.BillDetailAdapter;
import com.example.javatest.dao.BillDetailDAO;
import com.example.javatest.model.BillDetailItem;

import java.util.ArrayList;

public class BillDetailFragment extends Fragment {

    TextView txtBillId, txtTotal;
    RecyclerView rv;

    ArrayList<BillDetailItem> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bill_detail, container, false);

        txtBillId = view.findViewById(R.id.txtBillId);
        txtTotal = view.findViewById(R.id.txtTotalBill);
        rv = view.findViewById(R.id.rvDetail);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        int idBill = getArguments().getInt("idBill");
        txtBillId.setText("Mã Hóa Đơn: " + idBill);

        BillDetailDAO dao = new BillDetailDAO(getContext());
        Cursor c = dao.getBillDetail(idBill);

        double total = 0;

        while (c.moveToNext()) {

            String name = c.getString(0);
            int qty = c.getInt(1);
            double price = c.getDouble(2);

            total += price;

            list.add(new BillDetailItem(name, qty, price));
        }

        txtTotal.setText(
                "Tổng tiền: " + String.format("%,.0f VND", total)
        );
        BillDetailAdapter adapter = new BillDetailAdapter(list);
        rv.setAdapter(adapter);

        return view;

    }
}