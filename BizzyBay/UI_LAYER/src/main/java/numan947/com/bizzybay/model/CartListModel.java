package numan947.com.bizzybay.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListModel implements Parcelable {
    private int shopId;
    private String ShopName;
    private ArrayList<CartProductModel> cartProductModels;
    private String totalPrice;
    private int someID;//todo  // FIXME: 5/20/17

    public CartListModel(int shopId, String shopName, ArrayList<CartProductModel> cartProductModels, String totalPrice, int someID) {
        this.shopId = shopId;
        ShopName = shopName;
        this.cartProductModels = cartProductModels;
        this.totalPrice = totalPrice;
        this.someID = someID;
    }

    protected CartListModel(Parcel in) {
        shopId = in.readInt();
        ShopName = in.readString();
        cartProductModels = in.createTypedArrayList(CartProductModel.CREATOR);
        totalPrice = in.readString();
        someID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(shopId);
        dest.writeString(ShopName);
        dest.writeTypedList(cartProductModels);
        dest.writeString(totalPrice);
        dest.writeInt(someID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CartListModel> CREATOR = new Creator<CartListModel>() {
        @Override
        public CartListModel createFromParcel(Parcel in) {
            return new CartListModel(in);
        }

        @Override
        public CartListModel[] newArray(int size) {
            return new CartListModel[size];
        }
    };

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public ArrayList<CartProductModel> getCartProductModels() {
        return cartProductModels;
    }

    public void setCartProductModels(ArrayList<CartProductModel> cartProductModels) {
        this.cartProductModels = cartProductModels;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSomeID() {
        return someID;
    }

    public void setSomeID(int someID) {
        this.someID = someID;
    }
}
