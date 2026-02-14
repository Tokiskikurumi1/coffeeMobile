package com.example.javatest.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.ManageProductAdapter;
import com.example.javatest.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import android.content.Intent;

public class ManageProductFragment extends Fragment {

    RecyclerView recyclerView;
    ManageProductAdapter adapter;
    ArrayList<Product> list;
    FloatingActionButton fabAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_product, container, false);

        recyclerView = view.findViewById(R.id.rcvProduct);
        fabAdd = view.findViewById(R.id.fabAddProduct);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();

        // Ảnh để 0 tạm thời
        list.add(new Product(1,"Cà phê đen",45000,R.mipmap.ic_launcher,"Cà phê"));
        list.add(new Product(2,"Cà phê sữa đá",45000,R.mipmap.ic_launcher,"Cà phê"));
        list.add(new Product(3,"Trà sữa",45000,R.mipmap.ic_launcher,"Trà sữa"));
        list.add(new Product(4,"Sinh tố dâu",45000,R.mipmap.ic_launcher,"Sinh tố"));

        adapter = new ManageProductAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddProduct.class);
            startActivity(intent);
        });


        return view;
    }
}
