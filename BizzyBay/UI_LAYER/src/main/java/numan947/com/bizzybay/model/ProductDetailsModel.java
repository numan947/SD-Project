package numan947.com.bizzybay.model;

import java.util.ArrayList;

/**
 *
 * @author numan947
 * @since 5/3/17.<br>
 *
 *
 * Model element for the Product Details in the UI Layer
 **/

public class ProductDetailsModel {
    private int productId;
    private int shopId;
    private String productTitle;
    private String shopName;
    private String productPrice;
    private String shopLocation;
    private boolean isCarted;
    private boolean isLiked;
    private String productDetails;
    private ArrayList<String> productCategories;
    private ArrayList<String> productImages;


    public ProductDetailsModel(int productId, int shopId, String productTitle, String shopName, String productPrice,
                               String shopLocation, boolean isCarted, boolean isLiked, String productDetails,
                               ArrayList<String> productCategories, ArrayList<String> productImages) {
        this.productId = productId;
        this.shopId = shopId;
        this.productTitle = productTitle;
        this.shopName = shopName;
        this.productPrice = productPrice;
        this.shopLocation = shopLocation;
        this.isCarted = isCarted;
        this.isLiked = isLiked;
        this.productDetails = productDetails;
        this.productCategories = productCategories;
        this.productImages = productImages;


    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public void setCarted(boolean carted) {
        isCarted = carted;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public void setProductCategories(ArrayList<String> productCategories) {
        this.productCategories = productCategories;
    }

    public void setProductImages(ArrayList<String> productImages) {
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

    public ArrayList<String> getProductCategories() {
        return productCategories;
    }

    public ArrayList<String> getProductImages() {
        return productImages;
    }
}
