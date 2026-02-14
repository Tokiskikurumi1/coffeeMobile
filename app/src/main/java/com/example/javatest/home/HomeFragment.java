package com.example.javatest.home;

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
import com.example.javatest.adapter.SectionAdapter;
import com.example.javatest.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rvSection;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvSection = view.findViewById(R.id.rvSection);
        rvSection.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        List<Product> products = new ArrayList<>();

        products.add(new Product(1,"Cà phê đen",45000,R.mipmap.ic_launcher,"Cà phê truyền thống"));
        products.add(new Product(2,"Bạc xỉu",45000,R.mipmap.ic_launcher,"Cà phê truyền thống"));
        products.add(new Product(3,"Espresso",45000,R.mipmap.ic_launcher,"Cà phê máy"));
        products.add(new Product(4,"Cappuccino",45000,R.mipmap.ic_launcher,"Cà phê máy"));
        products.add(new Product(5,"Trà sữa trân châu",45000,R.mipmap.ic_launcher,"Trà sữa"));
        products.add(new Product(6,"Sinh tố xoài",45000,R.mipmap.ic_launcher,"Sinh tố"));
        products.add(new Product(7,"Cà phê đen",45000,R.mipmap.ic_launcher,"Cà phê truyền thống"));
        products.add(new Product(8,"Bạc xỉu",45000,R.mipmap.ic_launcher,"Cà phê truyền thống"));
        List<String> categories = Arrays.asList(
                "Cà phê truyền thống",
                "Cà phê máy",
                "Trà sữa",
                "Sinh tố"
        );

        SectionAdapter adapter =
                new SectionAdapter(getContext(), categories, products);

        rvSection.setAdapter(adapter);

        return view;
    }
}
