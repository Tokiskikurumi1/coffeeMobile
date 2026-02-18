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
import com.example.javatest.adapter.ProductAdapter;
import com.example.javatest.adapter.ProductVerticalAdapter;
import com.example.javatest.model.Product;

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
        List<Product> list = new ArrayList<>();

        list.add(new Product(1,"Cà phê đen",30000,R.mipmap.ic_launcher,"Cà phê"));
        list.add(new Product(2,"Cà phê sữa đá",45000,R.mipmap.ic_launcher,"Cà phê"));
        list.add(new Product(3,"Trà sữa",50000,R.mipmap.ic_launcher,"Trà sữa"));
        list.add(new Product(4,"Sinh tố dâu",60000,R.mipmap.ic_launcher,"Sinh tố"));


//        ProductAdapter adapter = new ProductAdapter(getContext(), list, product -> {
//
//            Bundle bundle = new Bundle();
//            bundle.putString("name", product.getName());
//            bundle.putInt("price", product.getPrice());
//            bundle.putInt("image", product.getImageResId());
//
//            ProductDetailFragment fragment = new ProductDetailFragment();
//            fragment.setArguments(bundle);
//
//            requireActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fragment)
//                    .addToBackStack(null)
//                    .commit();
//        });

        ProductVerticalAdapter adapter =
                new ProductVerticalAdapter(list, product -> {

                    Bundle bundle = new Bundle();
                    bundle.putString("name", product.getName());
                    bundle.putInt("price", product.getPrice());
                    bundle.putInt("image", product.getImageResId());

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
