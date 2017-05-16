package numan947.com.bizzybay.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 *
 * @author numan947
 * @since 5/3/17.<br>
 *
 *
 * Model element for the Product Details in the UI Layer
 **/

public class ProductDetailsModel implements Parcelable {
    private int productId;
    private int shopId;
    private String productTitle;
    private String shopName;
    private String productPrice;
    private String shopLocation;
    private boolean isCarted;
    private boolean isLiked;
    private String productDetails;
    private ArrayList<String> productCategories;
    private ArrayList<String> productImages;


    public ProductDetailsModel(int productId, int shopId, String productTitle, String shopName, String productPrice,
                               String shopLocation, boolean isCarted, boolean isLiked, String productDetails,
                               ArrayList<String> productCategories, ArrayList<String> productImages) {
        this.productId = productId;
        this.shopId = shopId;
        this.productTitle = productTitle;
        this.shopName = shopName;
        this.productPrice = productPrice;
        this.shopLocation = shopLocation;
        this.isCarted = isCarted;
        this.isLiked = isLiked;
        this.productDetails = productDetails;
        this.productCategories = productCategories;
        this.productImages = productImages;


    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public void setCarted(boolean carted) {
        isCarted = carted;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public void setProductCategories(ArrayList<String> productCategories) {
        this.productCategories = productCategories;
    }

    public void setProductImages(ArrayList<String> productImages) {
        this.productImages = productImages;
    }

    public int getProductId() {
        return productId;
    }

    public int getShopId() {
        return shopId;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getShopName() {
        return shopName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public boolean isCarted() {
        return isCarted;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public ArrayList<String> getProductCategories() {
        return productCategories;
    }

    public ArrayList<String> getProductImages() {
        return productImages;
    }


    //making the class parcelable


    protected ProductDetailsModel(Parcel in) {
        productId = in.readInt();
        shopId = in.readInt();
        productTitle = in.readString();
        shopName = in.readString();
        productPrice = in.readString();
        shopLocation = in.readString();
        isCarted = in.readByte() != 0;
        isLiked = in.readByte() != 0;
        productDetails = in.readString();
        productCategories = in.createStringArrayList();
        productImages = in.createStringArrayList();
    }

    public static final Creator<ProductDetailsModel> CREATOR = new Creator<ProductDetailsModel>() {
        @Override
        public ProductDetailsModel createFromParcel(Parcel in) {
            return new ProductDetailsModel(in);
        }

        @Override
        public ProductDetailsModel[] newArray(int size) {
            return new ProductDetailsModel[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeInt(shopId);
        dest.writeString(productTitle);
        dest.writeString(shopName);
        dest.writeString(productPrice);
        dest.writeString(shopLocation);
        dest.writeByte((byte) (isCarted ? 1 : 0));
        dest.writeByte((byte) (isLiked ? 1 : 0));
        dest.writeString(productDetails);
        dest.writeStringList(productCategories);
        dest.writeStringList(productImages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
