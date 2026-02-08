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
        list.add(new Product("Cà phê đen", 45000, 0, "Cà phê"));
        list.add(new Product("Cà phê sữa đá", 45000, 0, "Cà phê"));
        list.add(new Product("Trà sữa", 45000, 0, "Trà"));
        list.add(new Product("Sinh tố dâu", 45000, 0, "Sinh tố"));

        adapter = new ManageProductAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v ->
                Toast.makeText(getContext(), "Thêm sản phẩm", Toast.LENGTH_SHORT).show()
        );

        return view;
    }
}
