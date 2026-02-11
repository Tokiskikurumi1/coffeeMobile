package com.example.javatest.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.javatest.Login;
import com.example.javatest.MainActivity;
import com.example.javatest.R;
import com.example.javatest.account.RevenueFragment;
import com.example.javatest.account.ManageProductFragment;
import com.example.javatest.account.ManageStaff;

public class AccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Thông tin cá nhân
        LinearLayout layoutInfo = view.findViewById(R.id.btnProfile);
        layoutInfo.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // Thống kê doanh thu
        LinearLayout btnThongKe = view.findViewById(R.id.btnRevenue);

        btnThongKe.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new RevenueFragment())
                    .addToBackStack(null)
                    .commit();
        });


        // Quản lý sản phẩm
        LinearLayout btnSanPham = view.findViewById(R.id.btnManageProduct);

        btnSanPham.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ManageProductFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // Quản lý nhân viên
        LinearLayout btnNhanVien = view.findViewById(R.id.btnStaffStat);

        btnNhanVien.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ManageStaff())
                    .addToBackStack(null)
                    .commit();
        });

        // Thêm loại sản phẩm
        LinearLayout btnLoaiSP = view.findViewById(R.id.btnAddCategory);

        btnLoaiSP.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ManageProductFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // Đăng xuất
        LinearLayout btnLogOut = view.findViewById(R.id.btnLogout);

        btnLogOut.setOnClickListener(v -> {

            AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
            alert.setTitle("Thông báo");
            alert.setMessage("Bạn có muốn đăng xuất không?");
            alert.setCancelable(false);

            alert.setPositiveButton("Có", (dialog, which) -> {

                // 1. Xóa trạng thái đăng nhập
                SharedPreferences preferences = requireActivity()
                        .getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();
                editor.clear(); // hoặc remove("isLogin")
                editor.apply();

                // 2. Chuyển về Login
                Intent intent = new Intent(requireContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            });

            alert.setNegativeButton("Không", (dialog, which) -> {
                dialog.dismiss();
            });

            alert.show();
        });


        return view;
    }
}
