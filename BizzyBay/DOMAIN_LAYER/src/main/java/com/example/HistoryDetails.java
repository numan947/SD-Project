package com.example;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetails {

    private String productName;
    private String productOrderId;
    private String shopName;
    private String productQuantity;
    private String productPrice;
    private String productOrderTime;
    private String productDeliveryTime;
    private String productDeliveryLocation;
    private String paymentMethod;
    private String paymentId;
    private String productDetails;
    private String productImage;
    private int productId;
    private int shopId;

    public HistoryDetails(){}

    public HistoryDetails(String productName, String productOrderId, String shopName, String productQuantity, String productPrice, String productOrderTime, String productDeliveryTime, String productDeliveryLocation, String paymentMethod, String paymentId, String productDetails, String productImage, int productId, int shopId) {
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

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductOrderTime() {
        return productOrderTime;
    }

    public void setProductOrderTime(String productOrderTime) {
        this.productOrderTime = productOrderTime;
    }

    public String getProductDeliveryTime() {
        return productDeliveryTime;
    }

    public void setProductDeliveryTime(String productDeliveryTime) {
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
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
