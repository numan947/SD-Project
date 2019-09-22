package numan947.com.data_layer.entity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Class representing List Product in the Data layer.
 **/

public class ListProductEntity {
    //needed IDs

    private int shopID;


    private int productID;


    //product info
    private String productTitle;
    private int productPrice;

    //shop info
    private String shopName;

    //liked status
    private boolean isLiked;

    //productImage
    private String productImage;


    public ListProductEntity(int shopID, int productID, String productTitle, int productPrice, String shopName, boolean isLiked, String productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.shopName = shopName;
        this.isLiked = isLiked;
        this.productImage = productImage;
    }

    public int getShopID() {
        return shopID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
