package com.example.javatest.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.javatest.R;

public class StaffDetailActivity extends AppCompatActivity {

    EditText edtId, edtName, edtDob, edtGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_staff_detail);

        edtId = findViewById(R.id.editId);
        edtName = findViewById(R.id.editName);
        edtDob = findViewById(R.id.editDob);
        edtGender = findViewById(R.id.editGender);
        btnSave = findViewById(R.id.btnUpdate);

        edtId.setText(getIntent().getStringExtra("id"));
        edtName.setText(getIntent().getStringExtra("name"));
        edtDob.setText(getIntent().getStringExtra("dob"));
        edtGender.setText(getIntent().getStringExtra("gender"));

        edtId.setEnabled(false); // không cho sửa mã

        btnSave.setOnClickListener(v -> {
            // Sau này update SQLite
            finish();
        });
    }
}
