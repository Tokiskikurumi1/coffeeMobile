package com.example.javatest.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.StaffAdapter;
import com.example.javatest.model.Staff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManageStaff extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    ArrayList<Staff> list;
    StaffAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_staff, container, false);

        recyclerView = view.findViewById(R.id.rcvStaff);
        fabAdd = view.findViewById(R.id.fabAddStaff);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        list.add(new Staff("NV01", "Nguyễn Văn A", "01/01/2000", "Nam"));
        list.add(new Staff("NV02", "Trần Thị B", "02/02/2001", "Nữ"));

        adapter = new StaffAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddStaffActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
