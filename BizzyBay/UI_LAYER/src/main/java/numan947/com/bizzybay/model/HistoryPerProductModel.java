package numan947.com.bizzybay.model;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 * This Class Represents history per product in the history list.
 **/

public class HistoryPerProductModel {

    private int productId;
    private int shopId;
    private int orderId;

    private String productName;
    private String productQuantity;
    private String productStatus;
    private String ProductPrice;

    private String productImage;

    public HistoryPerProductModel(int productId, int shopId, int orderId, String productName, String productQuantity, String productStatus, String productPrice, String productImage) {
        this.productId = productId;
        this.shopId = shopId;
        this.orderId = orderId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productStatus = productStatus;
        ProductPrice = productPrice;
        this.productImage = productImage;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getProductId() {
        return productId;
    }

    public int getShopId() {
        return shopId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public String getProductPrice() {
        return ProductPrice;
    }
}
