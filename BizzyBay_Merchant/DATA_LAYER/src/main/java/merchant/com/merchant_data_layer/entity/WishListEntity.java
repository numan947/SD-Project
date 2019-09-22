package merchant.com.merchant_data_layer.entity;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class WishListEntity {
    private int productId;
    private int shopId;

    private String productName;
    private String shopName;
    private int productPrice;
    private String productImage;


    public WishListEntity(int productId, int shopId, String productName, String shopName, int productPrice, String productImage) {
        this.productId = productId;
        this.shopId = shopId;
        this.productName = productName;
        this.shopName = shopName;
        this.productPrice = productPrice;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
