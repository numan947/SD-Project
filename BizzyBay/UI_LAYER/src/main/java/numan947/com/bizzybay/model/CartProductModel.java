package numan947.com.bizzybay.model;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartProductModel {
    private String productName;
    private String productQuantity;
    private String  productPrice;
    private String  productAvailability;
    private String productImage;

    public CartProductModel(String productName, String productQuantity, String productPrice, String productAvailability, String productImage) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productAvailability = productAvailability;
        this.productImage = productImage;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(String productAvailability) {
        this.productAvailability = productAvailability;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
