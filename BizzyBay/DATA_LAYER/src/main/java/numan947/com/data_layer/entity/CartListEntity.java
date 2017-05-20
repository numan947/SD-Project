package numan947.com.data_layer.entity;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListEntity {
    private String ShopName;
    private ArrayList<CartProductEntity>cartProductEntities;
    private int totalPrice;
    private int someID;//todo  // FIXME: 5/20/17

    public CartListEntity(String shopName, ArrayList<CartProductEntity> cartProductEntities, int totalPrice, int someID) {
        ShopName = shopName;
        this.cartProductEntities = cartProductEntities;
        this.totalPrice = totalPrice;
        this.someID = someID;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public ArrayList<CartProductEntity> getCartProductEntities() {
        return cartProductEntities;
    }

    public void setCartProductEntities(ArrayList<CartProductEntity> cartProductEntities) {
        this.cartProductEntities = cartProductEntities;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSomeID() {
        return someID;
    }

    public void setSomeID(int someID) {
        this.someID = someID;
    }
}
