package numan947.com.data_layer.entity;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopListEntity {
    private int shopId;
    private String shopImage;
    private String shopDetails;
    private String shopType;
    private String shopLocation;
    private int shopDistance;
    private String shopName;


    public ShopListEntity(int shopId, String shopImage, String shopDetails, String shopType, String shopLocation, int shopDistance, String shopName) {
        this.shopId = shopId;
        this.shopImage = shopImage;
        this.shopDetails = shopDetails;
        this.shopType = shopType;
        this.shopLocation = shopLocation;
        this.shopDistance = shopDistance;
        this.shopName = shopName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopDetails() {
        return shopDetails;
    }

    public void setShopDetails(String shopDetails) {
        this.shopDetails = shopDetails;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public int getShopDistance() {
        return shopDistance;
    }

    public void setShopDistance(int shopDistance) {
        this.shopDistance = shopDistance;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
