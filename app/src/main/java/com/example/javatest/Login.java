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

public class Login extends AppCompatActivity {

    EditText UserName, Password;
    Button btnLogin;

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

        AlertDialog.Builder alter = new AlertDialog.Builder(Login.this);
        alter.setTitle("Thông báo");
        alter.setCancelable(false); // không bấm ra ngoài để tắt



        if (checkLogin(user, pass)) {
            alter.setMessage("Login success!")
                    .setPositiveButton("OK", (dialog, which) -> {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .show();

        } else {
            Toast.makeText(this,
                    "Sai tài khoản hoặc mật khẩu",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // ===============================
    // HÀM KIỂM TRA TÀI KHOẢN
    // ===============================
    private boolean checkLogin(String username, String password) {
        return username.equals("1") && password.equals("1");
    }
}

