package com.merchant_example;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetails {
    private int userId;

    private String userImageUrl;
    private String accountName;

    private String userName;
    private String phone;

    private String email;
    private String password;
    private String whatsapp;
    private String facebook;

    public UserDetails(int userId, String userImageUrl, String accountName, String userName, String phone, String email, String password, String whatsapp, String facebook) {
        this.userId = userId;
        this.userImageUrl = userImageUrl;
        this.accountName = accountName;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.whatsapp = whatsapp;
        this.facebook = facebook;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
