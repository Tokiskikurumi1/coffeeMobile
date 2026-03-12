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
import android.content.SharedPreferences;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    RecyclerView rvSection;
    TextView txtHello;
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvSection = view.findViewById(R.id.rvSection);
        // LẤY TÊN NGƯỜI ĐĂNG NHẬP HIỆN TẠI
        txtHello = view.findViewById(R.id.txtHello);
        SharedPreferences sp = getActivity().getSharedPreferences("USER",0);
        String name = sp.getString("name","");

        txtHello.setText("Xin chào: " + name);
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