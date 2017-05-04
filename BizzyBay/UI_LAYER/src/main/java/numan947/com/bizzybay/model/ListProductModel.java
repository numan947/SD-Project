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
    private int productLikedStatus;

    //productImage
    private URL productImage;//or URL?


    public ListProductModel(String shopID, String productID, String productTitle, String shopDetails, String productPrice, int productLikedStatus, URL productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.shopDetails = shopDetails;
        this.productPrice = productPrice;
        this.productLikedStatus = productLikedStatus;
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

    public int getProductLikedStatus() {
        return productLikedStatus;
    }

    public URL getProductImage() {
        return productImage;
    }
}
