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
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.SectionModel;

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

        // Layout dọc cho danh mục
        rvSection.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        // Load dữ liệu theo section
        ProductDAO dao = new ProductDAO(getContext());
        List<SectionModel> sections = dao.getHomeSections();

        SectionAdapter adapter = new SectionAdapter(getContext(), sections);

        rvSection.setAdapter(adapter);

        return view;
    }
}