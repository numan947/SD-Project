package merchant.com.merchant_data_layer.entity;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryPerProductEntity {
    private int productId;
    private int shopId;
    private int orderId;

    private String productName;
    private String productQuantity;
    private String productStatus;
    private String ProductPrice;

    private String productImage;

    public HistoryPerProductEntity(int productId, int shopId, int orderId, String productName, String productQuantity, String productStatus, String productPrice, String productImage) {
        this.productId = productId;
        this.shopId = shopId;
        this.orderId = orderId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productStatus = productStatus;
        ProductPrice = productPrice;
        this.productImage = productImage;
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

    public String getProductImage() {
        return productImage;
    }
}
