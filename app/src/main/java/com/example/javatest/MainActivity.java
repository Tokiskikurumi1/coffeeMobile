package com.example.javatest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.javatest.account.AccountFragment;
import com.example.javatest.cart.CartFragment;
import com.example.javatest.home.HomeFragment;
import com.example.javatest.product.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        // CHỈ load mặc định khi app mở lần đầu
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            if (item.getItemId() == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_product) {
                fragment = new ProductFragment();
            } else if (item.getItemId() == R.id.nav_cart) {
                fragment = new CartFragment();
            } else if (item.getItemId() == R.id.nav_account){
                fragment = new AccountFragment();
            }

            return loadFragment(fragment);
        });
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment == null) return false;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        return true;
    }
}
