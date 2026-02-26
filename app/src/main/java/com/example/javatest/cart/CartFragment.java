package com.example.javatest.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.CartAdapter;
import com.example.javatest.dao.BillDAO;
import com.example.javatest.dao.BillDetailDAO;
import com.example.javatest.model.CartItem;

import java.text.DecimalFormat;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.OnCartChangeListener {

    RecyclerView rvCart;
    TextView txtTotal, tvEmpty;

    CartAdapter adapter;
    List<CartItem> cartList;

    Button btnPay;


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

        btnPay = view.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(v -> payBill());

        return view;
    }

    private void payBill(){

        if(cartList.isEmpty()){
            Toast.makeText(getContext(),"Giỏ hàng trống",Toast.LENGTH_SHORT).show();
            return;
        }

        BillDAO billDAO = new BillDAO(getContext());
        BillDetailDAO detailDAO = new BillDetailDAO(getContext());

        // 🔥 tạo bill
        long idBill = billDAO.createBill();

        double total = 0;

        // 🔥 insert từng món
        for(CartItem item : cartList){

            detailDAO.insert(
                    (int)idBill,
                    item.getIdFood(),
                    item.getQuantity(),
                    item.getPrice()
            );

            total += item.getPrice() * item.getQuantity();
        }

        // 🔥 update tổng tiền
        billDAO.payBill((int)idBill,total);

        // 🔥 clear giỏ
        CartManager.clear();

        adapter.notifyDataSetChanged();
        updateUI();

        Toast.makeText(getContext(),"Thanh toán thành công",Toast.LENGTH_SHORT).show();
        billDAO.debugBills();
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

        double total = 0;

        for (CartItem item : cartList) {
            total += item.getPrice() * item.getQuantity();
        }

        DecimalFormat format = new DecimalFormat("###,### VNĐ");
        txtTotal.setText(format.format(total));
    }

    @Override
    public void onCartChanged() {
        adapter.notifyDataSetChanged();
        updateUI();
    }


}
