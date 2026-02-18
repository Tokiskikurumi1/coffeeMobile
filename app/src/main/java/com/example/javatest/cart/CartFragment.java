package com.example.javatest.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.CartAdapter;
import com.example.javatest.model.CartItem;

import java.text.DecimalFormat;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.OnCartChangeListener {

    RecyclerView rvCart;
    TextView txtTotal, tvEmpty;

    CartAdapter adapter;
    List<CartItem> cartList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rvCart = view.findViewById(R.id.rvCart);
        txtTotal = view.findViewById(R.id.txtTotal);
        tvEmpty = view.findViewById(R.id.tvEmpty);

        // 🔥 LẤY DATA TỪ CartManager
        cartList = CartManager.getCartList();

        adapter = new CartAdapter(cartList, this);

        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCart.setAdapter(adapter);

        updateUI();

        return view;
    }

    private void updateUI() {

        if (cartList.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCart.setVisibility(View.GONE);
            txtTotal.setText("0 VNĐ");
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCart.setVisibility(View.VISIBLE);
            calculateTotal();
        }
    }

    private void calculateTotal() {

        int total = 0;

        for (CartItem item : cartList) {
            total += item.getPrice() * item.getQuantity();
        }

        DecimalFormat format = new DecimalFormat("###,### VNĐ");
        txtTotal.setText(format.format(total));
    }

    @Override
    public void onCartChanged() {
        updateUI();
    }
}
