package com.example.javatest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javatest.database.DatabaseHelper;
import com.example.javatest.model.User;

import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    private DatabaseHelper dbHelper;

    public StaffDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // 🔹 Lấy danh sách staff (role = 1)
    public List<User> getAllStaff() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE role = 1", null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setIdUser(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setDob(cursor.getString(2));
                user.setGender(cursor.getString(3));
                user.setUserName(cursor.getString(4));
                user.setPassword(cursor.getString(5));
                user.setRole(cursor.getInt(6));

                list.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    // 🔹 Thêm staff
    public boolean insertStaff(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", user.getName());
        values.put("dob", user.getDob());
        values.put("gender", user.getGender());
        values.put("userName", user.getUserName());
        values.put("password", user.getPassword());
        values.put("role", 1); // staff

        long result = db.insert("user", null, values);
        return result != -1;
    }

    // 🔹 Cập nhật staff
    public boolean updateStaff(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", user.getName());
        values.put("dob", user.getDob());
        values.put("gender", user.getGender());

        int result = db.update("user", values,
                "idUser=?", new String[]{String.valueOf(user.getIdUser())});

        return result > 0;
    }

    // 🔹 Xóa staff
    public boolean deleteStaff(int idUser) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete("user",
                "idUser=?", new String[]{String.valueOf(idUser)});
        return result > 0;
    }
}