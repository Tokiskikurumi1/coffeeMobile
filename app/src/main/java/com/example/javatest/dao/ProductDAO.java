package com.example.javatest.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javatest.database.DatabaseHelper;
import com.example.javatest.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private DatabaseHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // ✅ INSERT PRODUCT
    public long insert(Product p) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idCate", p.getIdCate());
        values.put("nameFood", p.getNameFood());
        values.put("price", p.getPrice());
        values.put("image", p.getImage());

        return db.insert("menu", null, values);
    }

    // ✅ UPDATE PRODUCT
    public int update(Product p) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idCate", p.getIdCate());
        values.put("nameFood", p.getNameFood());
        values.put("price", p.getPrice());
        values.put("image", p.getImage());

        return db.update(
                "menu",
                values,
                "idFood=?",
                new String[]{String.valueOf(p.getIdFood())}
        );
    }

    // ✅ DELETE
    public int delete(int idFood) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("menu", "idFood=?", new String[]{String.valueOf(idFood)});
    }

    // ✅ GET ALL
    public List<Product> getAll() {

        List<Product> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM menu", null);

        if (c.moveToFirst()) {
            do {
                list.add(readProduct(c));
            } while (c.moveToNext());
        }

        c.close();
        return list;
    }

    // ✅ GET BY CATEGORY
    public List<Product> getByCategory(int idCate) {

        List<Product> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM menu WHERE idCate=?",
                new String[]{String.valueOf(idCate)}
        );

        if (c.moveToFirst()) {
            do {
                list.add(readProduct(c));
            } while (c.moveToNext());
        }

        c.close();
        return list;
    }

    // ✅ SEARCH NAME
    public List<Product> search(String keyword) {

        List<Product> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM menu WHERE nameFood LIKE ?",
                new String[]{"%" + keyword + "%"}
        );

        if (c.moveToFirst()) {
            do {
                list.add(readProduct(c));
            } while (c.moveToNext());
        }

        c.close();
        return list;
    }

    // 🔥 đọc 1 product từ cursor
    private Product readProduct(Cursor c) {

        Product p = new Product();

        p.setIdFood(c.getInt(c.getColumnIndexOrThrow("idFood")));
        p.setIdCate(c.getInt(c.getColumnIndexOrThrow("idCate")));
        p.setNameFood(c.getString(c.getColumnIndexOrThrow("nameFood")));
        p.setPrice(c.getDouble(c.getColumnIndexOrThrow("price")));
        p.setImage(c.getString(c.getColumnIndexOrThrow("image")));

        return p;
    }
}