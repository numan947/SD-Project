package numan947.com.bizzybay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author numan947
 * @since 5/18/17.<br>
 **/

public class ShopDetailsModelForMap implements Parcelable {
    private String snippet;
    private String title;
    private LatLng latLng;

    public ShopDetailsModelForMap(String snippet, String title, LatLng latLng) {
        this.snippet = snippet;
        this.title = title;
        this.latLng = latLng;
    }

    protected ShopDetailsModelForMap(Parcel in) {
        snippet = in.readString();
        title = in.readString();
        latLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(snippet);
        dest.writeString(title);
        dest.writeParcelable(latLng, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShopDetailsModelForMap> CREATOR = new Creator<ShopDetailsModelForMap>() {
        @Override
        public ShopDetailsModelForMap createFromParcel(Parcel in) {
            return new ShopDetailsModelForMap(in);
        }

        @Override
        public ShopDetailsModelForMap[] newArray(int size) {
            return new ShopDetailsModelForMap[size];
        }
    };

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
