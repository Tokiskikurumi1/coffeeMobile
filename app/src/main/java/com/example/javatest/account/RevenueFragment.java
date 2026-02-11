package com.example.javatest.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.RevenueAdapter;
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

        TextView btnBack = view.findViewById(R.id.btnBack);

        recyclerView = view.findViewById(R.id.rvRevenue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        list.add(new Revenue("HD001", "07/02/2026", 362000));
        list.add(new Revenue("HD002", "07/02/2026", 992000));
        list.add(new Revenue("HD003", "08/02/2026", 1036000));
        list.add(new Revenue("HD004", "08/02/2026", 1248000));

        adapter = new RevenueAdapter(list);
        recyclerView.setAdapter(adapter);

        btnBack.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
        });

        return view;
    }
}
