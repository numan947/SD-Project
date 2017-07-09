package merchant.com.bizzybay_merchant.model;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class WishListModel   {
    private int productId;
    private int shopId;

    private String productName;
    private String shopName;
    private String productPrice;
    private String productImage;

    public WishListModel(int productId, int shopId, String productName, String shopName, String productPrice, String productImage) {
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
