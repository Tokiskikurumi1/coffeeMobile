package com.example.javatest.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javatest.R;

public class AddProduct extends AppCompatActivity {

    Button btnCancel, btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_product);

        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);

        btnCancel.setOnClickListener(v -> finish());

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(this, "Đã thêm sản phẩm!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
