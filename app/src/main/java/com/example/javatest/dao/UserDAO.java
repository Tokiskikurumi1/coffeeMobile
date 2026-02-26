package com.example.javatest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javatest.database.DatabaseHelper;
import com.example.javatest.model.User;

public class UserDAO {

    DatabaseHelper helper;

    public UserDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    // ================= LOGIN =================
    public User checkLogin(String username,String password){

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM user WHERE userName=? AND password=?",
                new String[]{username,password});

        if(c.moveToFirst()){
            User u = new User();
            u.setIdUser(c.getInt(0));
            u.setName(c.getString(1));
            u.setDob(c.getString(2));
            u.setGender(c.getString(3));
            u.setUserName(c.getString(4));
            u.setPassword(c.getString(5));
            u.setRole(c.getInt(6));
            c.close();
            return u;
        }

        c.close();
        return null;
    }

    // ================= UPDATE PROFILE =================
    public boolean updateProfile(User u){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put("name",u.getName());
        v.put("dob",u.getDob());
        v.put("gender",u.getGender());
        v.put("password",u.getPassword());

        int r = db.update("user",v,
                "idUser=?",
                new String[]{String.valueOf(u.getIdUser())});

        return r>0;
    }

    // lấy user theo id
    public User getUserById(int id){

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM user WHERE idUser=?",
                new String[]{String.valueOf(id)});

        if(c.moveToFirst()){
            User u = new User();
            u.setIdUser(c.getInt(0));
            u.setName(c.getString(1));
            u.setDob(c.getString(2));
            u.setGender(c.getString(3));
            u.setUserName(c.getString(4));
            u.setPassword(c.getString(5));
            u.setRole(c.getInt(6));
            c.close();
            return u;
        }
        c.close();
        return null;
    }
}