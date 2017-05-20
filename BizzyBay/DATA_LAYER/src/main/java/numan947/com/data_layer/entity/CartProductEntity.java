package numan947.com.data_layer.entity;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartProductEntity {
    private String productName;
    private int productQuantity;
    private int productPrice;
    private int productAvailability;
    private String productImage;

    public CartProductEntity(String productName, int productQuantity, int productPrice, int productAvailability, String productImage) {
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

    public int getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(int productAvailability) {
        this.productAvailability = productAvailability;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
