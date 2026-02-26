package com.example.javatest.account;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javatest.R;
import com.example.javatest.dao.StaffDAO;
import com.example.javatest.model.User;

import java.util.Calendar;

public class StaffDetailActivity extends AppCompatActivity {

    EditText edtId, edtName, edtDob;
    Spinner spGender;
    Button btnUpdate;

    StaffDAO dao;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_staff_detail);

        edtId = findViewById(R.id.editId);
        edtName = findViewById(R.id.editName);
        edtDob = findViewById(R.id.editDob);
        spGender = findViewById(R.id.spGender);
        btnUpdate = findViewById(R.id.btnUpdate);

        dao = new StaffDAO(this);

        // spinner
        String[] genderList = {"Nam","Nữ","Khác"};
        spGender.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                genderList));

        // nhận data
        id = getIntent().getIntExtra("id",0);
        String name = getIntent().getStringExtra("name");
        String dob = getIntent().getStringExtra("dob");
        String gender = getIntent().getStringExtra("gender");

        edtId.setText(String.valueOf(id));
        edtName.setText(name);
        edtDob.setText(dob);

        // set gender position
        for(int i=0;i<genderList.length;i++){
            if(genderList[i].equals(gender)){
                spGender.setSelection(i);
                break;
            }
        }

        // date picker
        edtDob.setOnClickListener(v -> {

            Calendar c = Calendar.getInstance();

            new DatePickerDialog(this,
                    (view, year, month, day) -> {
                        edtDob.setText(day+"/"+(month+1)+"/"+year);
                    },
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        // update
        btnUpdate.setOnClickListener(v -> {

            User u = new User();
            u.setIdUser(id);
            u.setName(edtName.getText().toString());
            u.setDob(edtDob.getText().toString());
            u.setGender(spGender.getSelectedItem().toString());

            if(dao.updateStaff(u)){
                Toast.makeText(this,"Đã cập nhật",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this,"Lỗi update",Toast.LENGTH_SHORT).show();
            }
        });
    }
}