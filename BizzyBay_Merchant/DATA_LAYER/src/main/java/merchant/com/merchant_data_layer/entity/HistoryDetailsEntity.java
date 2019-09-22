package merchant.com.merchant_data_layer.entity;

import java.util.Date;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetailsEntity {
    private String productName;
    private int productOrderId;
    private String shopName;
    private int productQuantity;
    private int productPrice;
    private Date productOrderTime;
    private Date productDeliveryTime;
    private String productDeliveryLocation;
    private String paymentMethod;
    private int paymentId;
    private String productDetails;
    private String productImage;
    private int productId;
    private int shopId;

    public HistoryDetailsEntity(String productName, int productOrderId, String shopName, int productQuantity, int productPrice, Date productOrderTime, Date productDeliveryTime, String productDeliveryLocation, String paymentMethod, int paymentId, String productDetails, String productImage, int productId, int shopId) {
        this.productName = productName;
        this.productOrderId = productOrderId;
        this.shopName = shopName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productOrderTime = productOrderTime;
        this.productDeliveryTime = productDeliveryTime;
        this.productDeliveryLocation = productDeliveryLocation;
        this.paymentMethod = paymentMethod;
        this.paymentId = paymentId;
        this.productDetails = productDetails;
        this.productImage = productImage;
        this.productId = productId;
        this.shopId = shopId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public Date getProductOrderTime() {
        return productOrderTime;
    }

    public void setProductOrderTime(Date productOrderTime) {
        this.productOrderTime = productOrderTime;
    }

    public Date getProductDeliveryTime() {
        return productDeliveryTime;
    }

    public void setProductDeliveryTime(Date productDeliveryTime) {
        this.productDeliveryTime = productDeliveryTime;
    }

    public String getProductDeliveryLocation() {
        return productDeliveryLocation;
    }

    public void setProductDeliveryLocation(String productDeliveryLocation) {
        this.productDeliveryLocation = productDeliveryLocation;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
