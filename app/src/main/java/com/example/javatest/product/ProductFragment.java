package com.example.javatest.product;

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
import com.example.javatest.adapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    RecyclerView rcvProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_product, container, false);

        rcvProduct = view.findViewById(R.id.rcvProduct);

        // set layout dọc
        rcvProduct.setLayoutManager(new LinearLayoutManager(getContext()));

        // fake data test
        List<String> list = new ArrayList<>();
        list.add("Cà phê đen");
        list.add("Cà phê sữa đá");
        list.add("Trà sữa");
        list.add("Sinh tố dâu");
        list.add("Cà phê đen");
        list.add("Cà phê sữa đá");
        list.add("Trà sữa");
        list.add("Sinh tố dâu");

        ProductListAdapter adapter = new ProductListAdapter(list, productName -> {

            Bundle bundle = new Bundle();
            bundle.putString("name", productName);

            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        rcvProduct.setAdapter(adapter);
        return view;
    }
}
