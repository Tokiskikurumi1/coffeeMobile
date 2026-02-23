package com.example.javatest.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.SectionAdapter;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.Product;
import com.example.javatest.product.ProductDetailFragment;

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

        ProductDAO dao = new ProductDAO(getContext());
        List<Product> products = dao.getAll();

        rvSection.setLayoutManager(
                new GridLayoutManager(getContext(), 2)
        );

        HomeAdapter adapter =
                new HomeAdapter(getContext(), products, product -> {

                    Bundle b = new Bundle();
                    b.putInt("id", product.getIdFood());
                    b.putString("name", product.getNameFood());
                    b.putDouble("price", product.getPrice());
                    b.putString("image", product.getImage());

                    ProductDetailFragment f = new ProductDetailFragment();
                    f.setArguments(b);

                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, f)
                            .addToBackStack(null)
                            .commit();
                });

        rvSection.setAdapter(adapter);

        return view;
    }
}
