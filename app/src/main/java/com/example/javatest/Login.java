package com.example.javatest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import com.example.javatest.dao.UserDAO;
import com.example.javatest.model.User;

public class Login extends AppCompatActivity {

    EditText UserName, Password;
    Button btnLogin;
    UserDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginAccount), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dao = new UserDAO(this);
        // hàm ánh xạ
        anhXa();
    }
    // ===============================
    // HÀM ÁNH XẠ VIEW
    // ===============================
    private void anhXa() {
        UserName = findViewById(R.id.UserName);
        Password = findViewById(R.id.PassWord);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> handleLogin());
    }
    // ===============================
    // HÀM XỬ LÝ LOGIN
    // ===============================
    private void handleLogin() {

        String user = UserName.getText().toString().trim();
        String pass = Password.getText().toString().trim();

        User u = dao.checkLogin(user,pass);

        if(u!=null){

            // lưu id user đang login
            getSharedPreferences("USER",MODE_PRIVATE)
                    .edit()
                    .putInt("id",u.getIdUser())
                    .putInt("role",u.getRole())
                    .putString("name",u.getName())
                    .apply();

            Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();

        }else{
            Toast.makeText(this,"Sai tài khoản",Toast.LENGTH_SHORT).show();
        }
    }

    // ===============================
    // HÀM KIỂM TRA TÀI KHOẢN
    // ===============================
    private boolean checkLogin(String username, String password) {
        return username.equals("1") && password.equals("1");
    }
}

