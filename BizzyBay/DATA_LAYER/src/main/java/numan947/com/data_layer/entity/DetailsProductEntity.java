package numan947.com.data_layer.entity;

import java.util.ArrayList;

/**
 * Created by numan947 on 5/8/17.
 */

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
