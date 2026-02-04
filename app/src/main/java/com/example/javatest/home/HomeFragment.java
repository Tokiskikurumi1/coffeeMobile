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
import com.example.javatest.model.SectionModel;

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
                new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
        );

        // DATA GIẢ (test)
        List<SectionModel> sections = new ArrayList<>();

        sections.add(new SectionModel(
                "Sản phẩm bán chạy",
                Arrays.asList("Cà phê đen", "Trà sữa", "Latte") ));
        sections.add(new SectionModel(
                "Cà phê truyền thống",
                Arrays.asList("Cà phê sữa đá", "Bạc xỉu", "Cà phê đen đá") ));
        sections.add(new SectionModel(
                "Cà phê máy",
                Arrays.asList("Espresso", "Cappuccino", "Americano") ));
        sections.add(new SectionModel( "Trà sữa",
                Arrays.asList("Trà sữa trân châu", "Trà sữa matcha", "Trà sữa dừa nướng") ));
        sections.add(new SectionModel( "Sinh tố",
                Arrays.asList("Sinh tố dưa hấu", "Sinh tố bơ", "Sinh tố xoài") ));

        SectionAdapter adapter = new SectionAdapter(getContext(), sections);
        rvSection.setAdapter(adapter);

        return view;
    }
}
