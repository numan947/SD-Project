package com.example;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Representation of List Product in the domain layer.
 **/

public class ProductList {
    //needed IDs
    private int shopID;
    private int productID;


    //product info
    private String productTitle;
    private String productPrice;

    //shop info
    private String shopDetails;

    //liked status
    private boolean isLiked;

    //productImage
    private String productImage;


    public ProductList(int shopID, int productID, String productTitle, String productPrice, String shopDetails, boolean isLiked, String productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.shopDetails = shopDetails;
        this.isLiked = isLiked;
        this.productImage = productImage;
    }

    public int getShopID() {
        return shopID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getShopDetails() {
        return shopDetails;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public String getProductImage() {
        return productImage;
    }
}
