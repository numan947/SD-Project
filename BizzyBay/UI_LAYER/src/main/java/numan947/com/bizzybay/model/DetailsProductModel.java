package numan947.com.bizzybay.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by numan947 on 5/3/17.
 */

public class DetailsProductModel {
    private String productTitle;
    private String shopName;
    private String productPrice;
    private String shopLocation;
    private boolean isCarted;
    private boolean isLiked;
    private String productDetails;
    private ArrayList<String> productCategory;
    private ArrayList<String> productImages;
    private double shopLat;
    private double shopLng;

    public DetailsProductModel(String productTitle, String shopName, String productPrice,
                               String shopLocation, boolean isCarted, boolean isLiked, String productDetails,
                               ArrayList<String> productCategory, ArrayList<String> productImages,
                               double shopLat, double shopLng) {
        this.productTitle = productTitle;
        this.shopName = shopName;
        this.productPrice = productPrice;
        this.shopLocation = shopLocation;
        this.isCarted = isCarted;
        this.isLiked = isLiked;
        this.productDetails = productDetails;
        this.productCategory = productCategory;
        this.productImages = productImages;
        this.shopLat = shopLat;
        this.shopLng = shopLng;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public LatLng getShopPosition(){
        return new LatLng(getShopLat(),getShopLng());
    }

    public double getShopLat() {
        return shopLat;
    }

    public double getShopLng() {
        return shopLng;
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
