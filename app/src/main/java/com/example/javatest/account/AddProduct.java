package com.example.javatest.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javatest.R;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.Product;

public class AddProduct extends AppCompatActivity {

    EditText edtName, edtPrice, edtCategory;
    Button btnCancel, btnConfirm;

    ProductDAO dao;
    int idEdit = -1; // -1 = add, >0 = edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_product);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtCategory = findViewById(R.id.edtCategory);

        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);

        dao = new ProductDAO(this);

        // ✅ nhận id edit
        idEdit = getIntent().getIntExtra("id", -1);

        if(idEdit != -1){
            loadData(idEdit);
        }

        btnCancel.setOnClickListener(v -> finish());

        btnConfirm.setOnClickListener(v -> save());
    }

    void loadData(int id){

        for(Product p: dao.getAll()){
            if(p.getIdFood()==id){
                edtName.setText(p.getNameFood());
                edtPrice.setText(String.valueOf(p.getPrice()));
                edtCategory.setText(String.valueOf(p.getIdCate()));
                break;
            }
        }
    }

    void save(){

        String name = edtName.getText().toString();
        String priceStr = edtPrice.getText().toString();
        String cateStr = edtCategory.getText().toString();

        if(name.isEmpty() || priceStr.isEmpty() || cateStr.isEmpty()){
            Toast.makeText(this,"Nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int cate = Integer.parseInt(cateStr);

        Product p = new Product();
        p.setNameFood(name);
        p.setPrice(price);
        p.setIdCate(cate);
        p.setImage("coffee"); // tạm

        // ✅ EDIT
        if(idEdit!=-1){
            p.setIdFood(idEdit);
            dao.update(p);
            Toast.makeText(this,"Đã cập nhật",Toast.LENGTH_SHORT).show();
        }
        else{
            dao.insert(p);
            Toast.makeText(this,"Đã thêm",Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}