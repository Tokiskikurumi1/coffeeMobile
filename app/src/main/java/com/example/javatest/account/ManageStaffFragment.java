package com.example.javatest.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.StaffAdapter;
import com.example.javatest.dao.StaffDAO;
import com.example.javatest.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManageStaffFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fabAdd;

    ArrayList<User> list;
    StaffAdapter adapter;
    TextView btnBack;
    StaffDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_staff, container, false);

        recyclerView = view.findViewById(R.id.rcvStaff);
        fabAdd = view.findViewById(R.id.fabAddStaff);
        btnBack = view.findViewById(R.id.btnBack);
        dao = new StaffDAO(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();

        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddStaffActivity.class));
        });
        btnBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
        return view;
    }

    private void loadData(){
        list = new ArrayList<>(dao.getAllStaff());
        adapter = new StaffAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}