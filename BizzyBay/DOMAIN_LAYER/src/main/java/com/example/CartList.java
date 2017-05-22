package com.example;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartList {
    private String ShopName;
    private ArrayList<CartProduct> cartProducts;
    private String totalPrice;
    private int someID;//todo  // FIXME: 5/20/17
    private int shopId;

    public CartList(String shopName, ArrayList<CartProduct> cartProducts, String totalPrice, int someID, int shopId) {
        ShopName = shopName;
        this.cartProducts = cartProducts;
        this.totalPrice = totalPrice;
        this.someID = someID;
        this.shopId = shopId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public ArrayList<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSomeID() {
        return someID;
    }

    public void setSomeID(int someID) {
        this.someID = someID;
    }
}
