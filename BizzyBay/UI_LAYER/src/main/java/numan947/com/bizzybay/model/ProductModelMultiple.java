package numan947.com.bizzybay.model;

import android.graphics.Bitmap;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductModelMultiple {

    private String shopID;
    private String productID;
    private String productTitle;
    private String productShop;
    private String productPrice;
    private Bitmap productImage;//or URL?


    public ProductModelMultiple(String shopID,String productID, String productTitle, String productShop, String productPrice, Bitmap productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.productShop = productShop;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getShopID() {
        return shopID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductShop() {
        return productShop;
    }

    public void setProductShop(String productShop) {
        this.productShop = productShop;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }
}
