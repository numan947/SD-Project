package numan947.com.bizzybay.model;

import java.net.URL;

/**
 * Created by numan947 on 5/1/17.
 */

public class ListProductModel {

    //needed IDs
    private String shopID;
    private String productID;


    //product info
    private String productTitle;
    private String productPrice;

    //shop info
    private String shopDetails;

    //liked status
    private boolean isLiked;

    //productImage
    private URL productImage;


    public ListProductModel(String shopID, String productID, String productTitle, String shopDetails, String productPrice, boolean isLiked, URL productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.shopDetails = shopDetails;
        this.productPrice = productPrice;
        this.isLiked = isLiked;
        this.productImage = productImage;
    }

    public String getShopID() {
        return shopID;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getShopDetails() {
        return shopDetails;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public URL getProductImage() {
        return productImage;
    }
}
