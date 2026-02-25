package com.example.javatest.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.ManageProductAdapter;
import com.example.javatest.dao.CategoryDAO;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.Category;
import com.example.javatest.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import android.content.Intent;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ManageProductFragment extends Fragment {

    RecyclerView recyclerView;
    ManageProductAdapter adapter;
    ArrayList<Product> list;
    FloatingActionButton fabAdd;
    Spinner spFilter;
    CategoryDAO cateDAO;
    ArrayList<Category> cateList;
    EditText edtSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_product, container, false);
        spFilter = view.findViewById(R.id.spFilter);
        recyclerView = view.findViewById(R.id.rcvProduct);
        fabAdd = view.findViewById(R.id.fabAddProduct);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        edtSearch = view.findViewById(R.id.edtSearch);
        ProductDAO dao = new ProductDAO(getContext());
        list = new ArrayList<>(dao.getAll());

        adapter = new ManageProductAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);
        cateDAO = new CategoryDAO(getContext());
        cateList = new ArrayList<>(cateDAO.getAll());

        ArrayList<String> names = new ArrayList<>();
        names.add("Tất cả");

        for(Category c: cateList){
            names.add(c.getName());
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                names
        );

        spFilter.setAdapter(ad);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s,int st,int c,int a){}
            @Override public void onTextChanged(CharSequence s,int st,int b,int c){
                adapter.filter(s.toString());
            }
            @Override public void afterTextChanged(Editable s){}
        });
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

                ProductDAO dao = new ProductDAO(getContext());

                if(pos==0){
                    list.clear();
                    list.addAll(dao.getAll());
                }else{
                    int cateId = cateList.get(pos-1).getId();
                    list.clear();
                    list.addAll(dao.getByCategory(cateId));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });

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
