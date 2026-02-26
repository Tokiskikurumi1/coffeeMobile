package com.example.javatest.account;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javatest.R;
import com.example.javatest.dao.StaffDAO;
import com.example.javatest.model.User;

import java.util.Calendar;

public class AddStaffActivity extends AppCompatActivity {

    EditText edtName, edtDob, edtUser, edtPass;
    Spinner spGender;
    Button btnAdd;

    StaffDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_staff);

        edtName = findViewById(R.id.editName);
        edtDob = findViewById(R.id.editDob);
        edtUser = findViewById(R.id.editUser);
        edtPass = findViewById(R.id.editPass);
        spGender = findViewById(R.id.spGender);
        btnAdd = findViewById(R.id.btnAdd);

        dao = new StaffDAO(this);

        // ===== Spinner gender =====
        String[] genderList = {"Nam", "Nữ", "Khác"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        genderList);

        spGender.setAdapter(adapter);

        // ===== DatePicker =====
        edtDob.setOnClickListener(v -> {

            Calendar c = Calendar.getInstance();

            new DatePickerDialog(this,
                    (view, year, month, day) -> {
                        String date = day + "/" + (month + 1) + "/" + year;
                        edtDob.setText(date);
                    },
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        // ===== Add staff =====
        btnAdd.setOnClickListener(v -> {

            String name = edtName.getText().toString();
            String dob = edtDob.getText().toString();
            String gender = spGender.getSelectedItem().toString();
            String user = edtUser.getText().toString();
            String pass = edtPass.getText().toString();

            User u = new User(name, dob, gender, user, pass, 1);

            if (dao.insertStaff(u)) {
                Toast.makeText(this, "Đã thêm", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
            }
        });
    }
}