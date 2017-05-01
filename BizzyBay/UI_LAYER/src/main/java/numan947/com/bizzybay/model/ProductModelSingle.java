package numan947.com.bizzybay.model;

import android.graphics.Bitmap;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductModelSingle {

    private String shopID;
    private String productID;
    private String productTitle;
    private String productShop;
    private String productPrice;
    private String productDescription;
    private Bitmap[] productImage;//or URL?


    public ProductModelSingle(String shopID,String productID, String productTitle,String productDescription, String productShop, String productPrice, Bitmap[] productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.productShop = productShop;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productDescription = productDescription;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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

    public Bitmap[] getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap[] productImage) {
        this.productImage = productImage;
    }
}
