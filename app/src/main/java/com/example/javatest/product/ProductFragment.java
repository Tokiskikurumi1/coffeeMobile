package com.example.javatest.product;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.ProductVerticalAdapter;
import com.example.javatest.dao.CategoryDAO;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.Category;
import com.example.javatest.model.Product;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    RecyclerView recyclerView;
    ProductVerticalAdapter adapter;

    ArrayList<Product> list;
    Spinner spFilter;
    EditText edtSearch;

    CategoryDAO cateDAO;
    ArrayList<Category> cateList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_product, container, false);

        recyclerView = view.findViewById(R.id.rcvProduct);
        spFilter = view.findViewById(R.id.spFilter);
        edtSearch = view.findViewById(R.id.edtSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ProductDAO dao = new ProductDAO(getContext());
        list = new ArrayList<>(dao.getAll());

        adapter = new ProductVerticalAdapter(list, product -> {

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

        recyclerView.setAdapter(adapter);

        // ===== CATEGORY =====
        cateDAO = new CategoryDAO(getContext());
        cateList = new ArrayList<>(cateDAO.getAll());

        ArrayList<String> names = new ArrayList<>();
        names.add("Tất cả");

        for(Category c : cateList){
            names.add(c.getName());
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                names
        );

        spFilter.setAdapter(ad);

        // ===== FILTER CATEGORY =====
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

                ProductDAO dao = new ProductDAO(getContext());

                list.clear();

                if(pos==0){
                    list.addAll(dao.getAll());
                }else{
                    int cateId = cateList.get(pos-1).getId();
                    list.addAll(dao.getByCategory(cateId));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });

        // ===== SEARCH =====
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s,int st,int c,int a){}
            @Override public void afterTextChanged(Editable s){}

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c){

                ProductDAO dao = new ProductDAO(getContext());
                String keyword = s.toString().toLowerCase();

                list.clear();

                for(Product p : dao.getAll()){
                    if(p.getNameFood().toLowerCase().contains(keyword)){
                        list.add(p);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    // 🔥 reload khi thêm sản phẩm
    @Override
    public void onResume() {
        super.onResume();

        ProductDAO dao = new ProductDAO(getContext());
        list.clear();
        list.addAll(dao.getAll());
        adapter.notifyDataSetChanged();
    }
}