package numan947.com.bizzybay.model;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by numan947 on 5/3/17.
 */

public class DetailsProductModel {
    private String productTitle;
    private String shopName;
    private String productPrice;
    private boolean isCarted;
    private boolean isLiked;
    private String productDetails;
    private String productCategory;
    private ArrayList<URL> productImages;

    public DetailsProductModel(String productTitle, String shopName, String productPrice, boolean isCarted, boolean isLiked, String productDetails, String productCategory, ArrayList<URL> productImages) {
        this.productTitle = productTitle;
        this.shopName = shopName;
        this.productPrice = productPrice;
        this.isCarted = isCarted;
        this.isLiked = isLiked;
        this.productDetails = productDetails;
        this.productCategory = productCategory;
        this.productImages = productImages;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getShopName() {
        return shopName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public boolean isCarted() {
        return isCarted;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public ArrayList<URL> getProductImages() {
        return productImages;
    }
}
