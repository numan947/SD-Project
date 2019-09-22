package com.merchant_example;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopDetails {
    private String shopDetailsAboutUs;
    private String shopDetailsAboutUsImage;
    private String shopName;
    private String shopAddressLine1;
    private String shopAddressLine2;
    private String shopAddressLine3;
    private String shopDetailsPhone;
    private String shopDetailsAuxPhone;
    private String shopDetailsCity;
    private String shopDetailsZip;
    private ArrayList<String> shopDetailsImageViewPagerImages;
    private float lat;
    private float lng;

    private String shopUserName;
    private String shopId;

    private String faceBookPage;
    private String whatsappNumber;

    public ShopDetails(){

    }


    public ShopDetails(String shopDetailsAboutUs, String shopDetailsAboutUsImage,
                       String shopName, String shopAddressLine1, String shopAddressLine2,
                       String shopAddressLine3, String shopDetailsPhone, String shopDetailsAuxPhone,
                       String shopDetailsCity, String shopDetailsZip,
                       ArrayList<String> shopDetailsImageViewPagerImages, float lat, float lng,
                       String shopUserName, String shopId,String faceBookPage,String whatsappNumber) {
        this.shopDetailsAboutUs = shopDetailsAboutUs;
        this.shopDetailsAboutUsImage = shopDetailsAboutUsImage;
        this.shopName = shopName;
        this.shopAddressLine1 = shopAddressLine1;
        this.shopAddressLine2 = shopAddressLine2;
        this.shopAddressLine3 = shopAddressLine3;
        this.shopDetailsPhone = shopDetailsPhone;
        this.shopDetailsAuxPhone = shopDetailsAuxPhone;
        this.shopDetailsCity = shopDetailsCity;
        this.shopDetailsZip = shopDetailsZip;
        this.shopDetailsImageViewPagerImages = shopDetailsImageViewPagerImages;
        this.lat = lat;
        this.lng = lng;
        this.shopUserName = shopUserName;
        this.shopId = shopId;
        this.faceBookPage =faceBookPage;
        this.whatsappNumber  =whatsappNumber;
    }

    public String getFaceBookPage() {
        return faceBookPage;
    }

    public void setFaceBookPage(String faceBookPage) {
        this.faceBookPage = faceBookPage;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getShopDetailsAboutUs() {
        return shopDetailsAboutUs;
    }

    public void setShopDetailsAboutUs(String shopDetailsAboutUs) {
        this.shopDetailsAboutUs = shopDetailsAboutUs;
    }

    public String getShopDetailsAboutUsImage() {
        return shopDetailsAboutUsImage;
    }

    public void setShopDetailsAboutUsImage(String shopDetailsAboutUsImage) {
        this.shopDetailsAboutUsImage = shopDetailsAboutUsImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddressLine1() {
        return shopAddressLine1;
    }

    public void setShopAddressLine1(String shopAddressLine1) {
        this.shopAddressLine1 = shopAddressLine1;
    }

    public String getShopAddressLine2() {
        return shopAddressLine2;
    }

    public void setShopAddressLine2(String shopAddressLine2) {
        this.shopAddressLine2 = shopAddressLine2;
    }

    public String getShopAddressLine3() {
        return shopAddressLine3;
    }

    public void setShopAddressLine3(String shopAddressLine3) {
        this.shopAddressLine3 = shopAddressLine3;
    }

    public String getShopDetailsPhone() {
        return shopDetailsPhone;
    }

    public void setShopDetailsPhone(String shopDetailsPhone) {
        this.shopDetailsPhone = shopDetailsPhone;
    }

    public String getShopDetailsAuxPhone() {
        return shopDetailsAuxPhone;
    }

    public void setShopDetailsAuxPhone(String shopDetailsAuxPhone) {
        this.shopDetailsAuxPhone = shopDetailsAuxPhone;
    }

    public String getShopDetailsCity() {
        return shopDetailsCity;
    }

    public void setShopDetailsCity(String shopDetailsCity) {
        this.shopDetailsCity = shopDetailsCity;
    }

    public String getShopDetailsZip() {
        return shopDetailsZip;
    }

    public void setShopDetailsZip(String shopDetailsZip) {
        this.shopDetailsZip = shopDetailsZip;
    }

    public ArrayList<String> getShopDetailsImageViewPagerImages() {
        return shopDetailsImageViewPagerImages;
    }

    public void setShopDetailsImageViewPagerImages(ArrayList<String> shopDetailsImageViewPagerImages) {
        this.shopDetailsImageViewPagerImages = shopDetailsImageViewPagerImages;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getShopUserName() {
        return shopUserName;
    }

    public void setShopUserName(String shopUserName) {
        this.shopUserName = shopUserName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
