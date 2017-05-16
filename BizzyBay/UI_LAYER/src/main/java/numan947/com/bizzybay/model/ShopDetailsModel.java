package numan947.com.bizzybay.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopDetailsModel implements Parcelable {
    protected ShopDetailsModel(Parcel in) {
    }

    public static final Creator<ShopDetailsModel> CREATOR = new Creator<ShopDetailsModel>() {
        @Override
        public ShopDetailsModel createFromParcel(Parcel in) {
            return new ShopDetailsModel(in);
        }

        @Override
        public ShopDetailsModel[] newArray(int size) {
            return new ShopDetailsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
