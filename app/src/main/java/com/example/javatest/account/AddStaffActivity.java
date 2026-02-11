package com.example.javatest.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.javatest.R;


public class AddStaffActivity extends AppCompatActivity {

    EditText edtId, edtName, edtDob, edtGender;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_staff);

        edtId = findViewById(R.id.editId);
        edtName = findViewById(R.id.editName);
        edtDob = findViewById(R.id.editDob);
        edtGender = findViewById(R.id.editGender);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {
            // Sau này thêm SQLite
            finish();
        });
    }
}
