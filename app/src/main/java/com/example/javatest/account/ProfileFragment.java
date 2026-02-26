package com.example.javatest.account;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.fragment.app.Fragment;

import com.example.javatest.R;
import com.example.javatest.dao.UserDAO;
import com.example.javatest.model.User;

import java.util.Calendar;

public class ProfileFragment extends Fragment {

    EditText edtName,edtDob,edtUsername,edtPassword;
    Spinner spGender;
    Button btnSave;

    UserDAO dao;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        edtName=view.findViewById(R.id.edtName);
        edtDob=view.findViewById(R.id.edtDob);
        edtUsername=view.findViewById(R.id.edtUsername);
        edtPassword=view.findViewById(R.id.edtPassword);
        spGender=view.findViewById(R.id.spGender);
        btnSave=view.findViewById(R.id.btnSave);

        dao=new UserDAO(getContext());

        // spinner gender
        String[] g={"Nam","Nữ","Khác"};
        spGender.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,g));

        // lấy id đang login
        id=requireActivity()
                .getSharedPreferences("USER",0)
                .getInt("id",0);

        loadData();

        // date picker
        edtDob.setOnClickListener(v->{
            Calendar c=Calendar.getInstance();
            new DatePickerDialog(getContext(),
                    (view1,y,m,d)-> edtDob.setText(d+"/"+(m+1)+"/"+y),
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        btnSave.setOnClickListener(v->update());

        return view;
    }

    private void loadData(){

        User u=dao.getUserById(id);
        if(u==null) return;

        edtName.setText(u.getName());
        edtDob.setText(u.getDob());
        edtUsername.setText(u.getUserName());
        edtUsername.setEnabled(false);
        edtPassword.setText(u.getPassword());

        String[] g={"Nam","Nữ","Khác"};
        for(int i=0;i<g.length;i++){
            if(g[i].equals(u.getGender())){
                spGender.setSelection(i);
                break;
            }
        }
    }

    private void update(){

        User u=new User();
        u.setIdUser(id);
        u.setName(edtName.getText().toString());
        u.setDob(edtDob.getText().toString());
        u.setGender(spGender.getSelectedItem().toString());
        u.setPassword(edtPassword.getText().toString());

        if(dao.updateProfile(u))
            Toast.makeText(getContext(),"Đã cập nhật",Toast.LENGTH_SHORT).show();
    }
}