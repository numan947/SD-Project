package merchant.com.bizzybay_merchant.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartProductModel implements Parcelable {
    private String productName;
    private String productQuantity;
    private String  productPrice;
    private String  productAvailability;
    private String productImage;
    private int productId;

    public CartProductModel(String productName, String productQuantity, String productPrice, String productAvailability, String productImage, int productId) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productAvailability = productAvailability;
        this.productImage = productImage;
        this.productId = productId;
    }

    protected CartProductModel(Parcel in) {
        productName = in.readString();
        productQuantity = in.readString();
        productPrice = in.readString();
        productAvailability = in.readString();
        productImage = in.readString();
        productId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productQuantity);
        dest.writeString(productPrice);
        dest.writeString(productAvailability);
        dest.writeString(productImage);
        dest.writeInt(productId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CartProductModel> CREATOR = new Creator<CartProductModel>() {
        @Override
        public CartProductModel createFromParcel(Parcel in) {
            return new CartProductModel(in);
        }

        @Override
        public CartProductModel[] newArray(int size) {
            return new CartProductModel[size];
        }
    };

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
