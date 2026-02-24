package com.example.javatest.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.ProductAdapter;
import com.example.javatest.adapter.ProductVerticalAdapter;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    RecyclerView rcvProduct;
    List<Product> listFilter;
    EditText edtSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_product, container, false);

        rcvProduct = view.findViewById(R.id.rcvProduct);
        edtSearch = view.findViewById(R.id.edtSearch);

        // set layout dọc
        rcvProduct.setLayoutManager(new LinearLayoutManager(getContext()));

        ProductDAO dao = new ProductDAO(getContext());
        List<Product> list = dao.getAll();
        listFilter = new ArrayList<>(list);
        ProductVerticalAdapter adapter = new ProductVerticalAdapter(listFilter, product -> {

            Bundle bundle = new Bundle();

            bundle.putInt("id", product.getIdFood());
            bundle.putInt("cate", product.getIdCate());
            bundle.putString("name", product.getNameFood());
            bundle.putDouble("price", product.getPrice());
            bundle.putString("image", product.getImage());

            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        rcvProduct.setAdapter(adapter);

        edtSearch.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String keyword = s.toString().toLowerCase();

                listFilter.clear();

                for(Product p : list){
                    if(p.getNameFood().toLowerCase().contains(keyword)){
                        listFilter.add(p);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        return view;
    }
}
