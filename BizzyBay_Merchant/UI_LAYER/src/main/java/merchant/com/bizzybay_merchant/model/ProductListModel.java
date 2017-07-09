package merchant.com.bizzybay_merchant.model;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Product Model of the list element in the UI layer.
 **/

public class ProductListModel {

    //needed IDs
    private int shopID;
    private int productID;


    //product info
    private String productTitle;
    private String productPrice;

    //shop info
    private String shopDetails;

    //liked status
    private boolean isLiked;

    //productImage
    private String productImage;


    public ProductListModel(int shopID, int productID, String productTitle, String shopDetails, String productPrice, boolean isLiked, String productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.shopDetails = shopDetails;
        this.productPrice = productPrice;
        this.isLiked = isLiked;
        this.productImage = productImage;
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

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setShopDetails(String shopDetails) {
        this.shopDetails = shopDetails;
    }

    public void setProductImage(String productImage) {
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

    public String getShopDetails() {
        return shopDetails;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getProductImage() {
        return productImage;
    }
}
