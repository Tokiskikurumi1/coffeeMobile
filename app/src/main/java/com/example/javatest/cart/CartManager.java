package com.example.javatest.cart;

import com.example.javatest.model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static final List<CartItem> cartList = new ArrayList<>();

    public static List<CartItem> getCartList() {
        return cartList;
    }

    // 🔥 thêm hoặc cộng số lượng
    public static void addToCart(CartItem newItem) {

        for (CartItem item : cartList) {
            if (item.getIdFood() == newItem.getIdFood()) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }

        cartList.add(newItem);
    }

    // 🔥 tăng
    public static void increase(int position) {
        CartItem item = cartList.get(position);
        item.setQuantity(item.getQuantity() + 1);
    }

    // 🔥 giảm (về 0 → xóa)
    public static void decrease(int position) {

        CartItem item = cartList.get(position);

        if (item.getQuantity() <= 1) {
            cartList.remove(position);
        } else {
            item.setQuantity(item.getQuantity() - 1);
        }
    }

    public static void clear() {
        cartList.clear();
    }
}