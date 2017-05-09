package com.example;

import java.util.ArrayList;

/**
 *
 * @author numan947
 * @since 5/8/17.<br>
 *
 * Representation of Details Product in the domain layer
 **/

public class DetailsProduct {
    private int productId;
    private int shopId;
    private String productTitle;
    private String shopName;
    private String productPrice;
    private String shopLocation;
    private boolean isCarted;
    private boolean isLiked;
    private String productDetails;
    private ArrayList<String> productCategory;
    private ArrayList<String> productImages;


    public DetailsProduct(int productId, int shopId, String productTitle, String shopName, String productPrice,
                          String shopLocation, boolean isCarted, boolean isLiked, String productDetails,
                          ArrayList<String> productCategory, ArrayList<String> productImages) {
        this.productId = productId;
        this.shopId = shopId;
        this.productTitle = productTitle;
        this.shopName = shopName;
        this.productPrice = productPrice;
        this.shopLocation = shopLocation;
        this.isCarted = isCarted;
        this.isLiked = isLiked;
        this.productDetails = productDetails;
        this.productCategory = productCategory;
        this.productImages = productImages;


    }

    public int getProductId() {
        return productId;
    }

    public int getShopId() {
        return shopId;
    }

    public String getShopLocation() {
        return shopLocation;
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

    public ArrayList<String> getProductCategory() {
        return productCategory;
    }

    public ArrayList<String> getProductImages() {
        return productImages;
    }
}
