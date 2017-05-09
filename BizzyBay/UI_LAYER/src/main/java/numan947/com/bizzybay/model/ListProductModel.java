package numan947.com.bizzybay.model;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Product Model of the list element in the UI layer.
 **/

public class ListProductModel {

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


    public ListProductModel(int shopID, int productID, String productTitle, String shopDetails, String productPrice, boolean isLiked, String productImage) {
        this.shopID = shopID;
        this.productID = productID;
        this.productTitle = productTitle;
        this.shopDetails = shopDetails;
        this.productPrice = productPrice;
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
