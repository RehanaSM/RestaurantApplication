package com.restaurantapplication.model;


public class RestaurantModel {

    /**
     * restaurant_name : Spill
     * address : J-349, JP Road, Opp. Apna Bazar, JP Rd, D.N.Nagar, Andheri West, Mumbai, Maharashtra, India
     * latitude : 19.127473
     * longitude : 72.832545
     * phone_number : +91 22 2642 5895
     * logo_url : http://image6.buzzintown.com/files/venue/upload_20000/upload_original/484402-spill-resto-bar.jpg
     */

    private String restaurant_name;
    private String address;
    private String latitude;
    private String longitude;
    private String phone_number;
    private String logo_url;

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getLogo_url() {
        return logo_url;
    }
}
