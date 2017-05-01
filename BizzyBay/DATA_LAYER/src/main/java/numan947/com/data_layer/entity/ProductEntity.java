package numan947.com.data_layer.entity;

import java.net.URL;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductEntity {

    private int productId;
    private String productTitle;
    private int productPrice;
    private String productStore;
    private int storeId;
    private URL productImageUrl;


    public ProductEntity(int productId, String productTitle, int productPrice, String productStore, int storeId, URL productImageUrl) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productStore = productStore;
        this.storeId = storeId;
        this.productImageUrl = productImageUrl;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductStore() {
        return productStore;
    }

    public int getStoreId() {
        return storeId;
    }

    public URL getProductImageUrl() {
        return productImageUrl;
    }
}
