package com.example.javatest.product;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javatest.R;
import com.example.javatest.cart.CartManager;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.CartItem;
import com.example.javatest.model.Product;
import com.example.javatest.util.ImageLoader;

import java.util.Locale;

public class ProductDetailFragment extends Fragment {

    ImageView imgProduct;
    TextView txtName, txtPrice, txtQuantity, txtTotal;
    Button btnAddToCart, btnPlus, btnMinus;

    Product product;
    int quantity = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        imgProduct = view.findViewById(R.id.imgProduct);
        txtName = view.findViewById(R.id.txtName);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtQuantity = view.findViewById(R.id.txtQuantity);
        txtTotal = view.findViewById(R.id.txtTotal);

        btnAddToCart = view.findViewById(R.id.btnAddToCart);
        btnPlus = view.findViewById(R.id.btnPlus);
        btnMinus = view.findViewById(R.id.btnMinus);

        // 🔥 LẤY ID → QUERY DB
        if (getArguments() != null) {

            int id = getArguments().getInt("id",-1);

            ProductDAO dao = new ProductDAO(getContext());
            product = dao.getById(id);

            if(product!=null){
                txtName.setText(product.getNameFood());
                txtPrice.setText(product.getPrice()+" VNĐ");

                ImageLoader.load(imgProduct, product.getImage());
            }
        }

        txtQuantity.setText(String.valueOf(quantity));
        updateTotal();

        btnPlus.setOnClickListener(v -> {
            quantity++;
            txtQuantity.setText(String.valueOf(quantity));
            updateTotal();
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                txtQuantity.setText(String.valueOf(quantity));
                updateTotal();
            }
        });

        btnAddToCart.setOnClickListener(v -> {

            if(product==null) return;

            CartItem item = new CartItem(
                    product.getIdFood(),
                    product.getNameFood(),
                    product.getPrice(),
                    quantity,
                    product.getImage()
            );

            CartManager.addToCart(item);

            Toast.makeText(getContext(),"Đã thêm vào giỏ",Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void updateTotal() {
        if(product==null) return;
        double total = product.getPrice()*quantity;
//        txtTotal.setText("Tổng: "+total+" VNĐ");
        txtTotal.setText("Tổng: " +
                String.format(new Locale("vi","VN"), "%,.0f VND", total)
        );
    }
}