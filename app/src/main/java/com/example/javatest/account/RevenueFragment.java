package com.example.javatest.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.RevenueAdapter;
import com.example.javatest.dao.BillDAO;
import com.example.javatest.model.Revenue;

import java.util.ArrayList;

public class RevenueFragment extends Fragment {

    RecyclerView recyclerView;
    RevenueAdapter adapter;
    ArrayList<Revenue> list;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_revenue, container, false);

        recyclerView = view.findViewById(R.id.rvRevenue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BillDAO billDAO = new BillDAO(getContext());
        list = billDAO.getAllRevenue();

        adapter = new RevenueAdapter(list, idBill -> {

            Bundle b = new Bundle();
            b.putInt("idBill", idBill);

            BillDetailFragment f = new BillDetailFragment();
            f.setArguments(b);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, f)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}