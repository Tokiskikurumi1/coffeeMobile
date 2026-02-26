package com.example.javatest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "coffee_app.db";
    private static final int DB_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // USER
        db.execSQL("CREATE TABLE user(" +
                "idUser INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "dob TEXT," +
                "gender TEXT," +
                "userName TEXT UNIQUE," +
                "password TEXT," +
                "role INTEGER)");

        // CATEGORY
        db.execSQL("CREATE TABLE category(" +
                "idCate INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cateName TEXT)");

        // MENU
        db.execSQL("CREATE TABLE menu(" +
                "idFood INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idCate INTEGER," +
                "nameFood TEXT," +
                "price REAL," +
                "image TEXT," +
                "FOREIGN KEY(idCate) REFERENCES category(idCate))");

        // BILL
        db.execSQL("CREATE TABLE bill(" +
                "idBill INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "totalPrice REAL)");

        // BILL DETAIL
        db.execSQL("CREATE TABLE billDetail(" +
                "idBill INTEGER," +
                "idFood INTEGER," +
                "amount INTEGER," +
                "price REAL," +
                "totalPrice REAL," +
                "PRIMARY KEY(idBill,idFood)," +
                "FOREIGN KEY(idBill) REFERENCES bill(idBill)," +
                "FOREIGN KEY(idFood) REFERENCES menu(idFood))");

        // ===== INSERT CATEGORY =====
        db.execSQL("INSERT INTO category VALUES(1,'Cà phê')");
        db.execSQL("INSERT INTO category VALUES(2,'Trà sữa')");
        db.execSQL("INSERT INTO category VALUES(3,'Đá xay')");

        // ===== INSERT MENU =====
        db.execSQL("INSERT INTO menu VALUES(1,1,'Cà phê đen',30000,'cf_den')");
        db.execSQL("INSERT INTO menu VALUES(2,1,'Cà phê sữa',35000,'cf_den')");
        db.execSQL("INSERT INTO menu VALUES(3,1,'Bạc xỉu',38000,'cf_den')");
        db.execSQL("INSERT INTO menu VALUES(4,1,'Americano',40000,'cf_den')");
        db.execSQL("INSERT INTO menu VALUES(5,1,'Latte',42000,'cf_den')");

        db.execSQL("INSERT INTO menu VALUES(6,2,'Trà sữa trân châu',45000,'cf_den')");
        db.execSQL("INSERT INTO menu VALUES(7,2,'Trà sữa matcha',48000,'cf_den')");
        db.execSQL("INSERT INTO menu VALUES(8,2,'Trà sữa socola',47000,'cf_den')");

        db.execSQL("INSERT INTO menu VALUES(9,3,'Socola đá xay',50000,'cf_den')");
        db.execSQL("INSERT INTO menu VALUES(10,3,'Matcha đá xay',52000,'cf_den')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS billDetail");
        db.execSQL("DROP TABLE IF EXISTS bill");
        db.execSQL("DROP TABLE IF EXISTS menu");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS user");

        onCreate(db);
    }
}