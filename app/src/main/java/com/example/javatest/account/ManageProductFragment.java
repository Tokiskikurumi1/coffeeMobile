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
import com.example.javatest.dao.ProductDAO;
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

        ProductDAO dao = new ProductDAO(getContext());
        list = new ArrayList<>(dao.getAll());

        adapter = new ManageProductAdapter(getContext(), list, new ManageProductAdapter.OnAction() {

            @Override
            public void onEdit(Product p) {

                Intent i = new Intent(getContext(), AddProduct.class);
                i.putExtra("id",p.getIdFood());
                startActivity(i);
            }

            @Override
            public void onDelete(Product p) {

                dao.delete(p.getIdFood());

                list.remove(p);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddProduct.class);
            startActivity(intent);
        });


        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        ProductDAO dao = new ProductDAO(getContext());
        list.clear();
        list.addAll(dao.getAll());
        adapter.notifyDataSetChanged();
    }
}
