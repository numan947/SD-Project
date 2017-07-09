package merchant.com.merchant_data_layer.entity;

import java.util.ArrayList;

/**
 *
 * @author numan947
 * @since 5/8/17.<br>
 *
 * Class representing Details Product in the Data Layer
 **/

public class DetailsProductEntity {
    private int productId;
    private int shopId;
    private String productTitle;
    private String shopName;
    private int productPrice;
    private String shopLocation;
    private boolean isCarted;
    private boolean isLiked;
    private String productDetails;
    private ArrayList<String> productCategory;
    private ArrayList<String> productImages;

    public DetailsProductEntity(){}


    public DetailsProductEntity(int productId, int shopId, String productTitle, String shopName, int productPrice,
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

    public void setProductPrice(int productPrice) {
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

    public void setProductCategory(ArrayList<String> productCategory) {
        this.productCategory = productCategory;
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

    public int getProductPrice() {
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
