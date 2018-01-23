package com.tech20.mobiledelivery.retrofitclient.loginclient;

import com.google.gson.annotations.SerializedName;

/**
 *  {
 "api_key": "d95f8196-6a02-458b-b7fd-1102a4525eba",
 "api_secret": "F9C4545E12BA699D0D69EEE1A5675B4B",
 "DriverId": 1,
 "storeDetails": {
                 "id": 5141,
                 "first_name": "FidelGlobal",
                 "last_name": "",
                 "shop_name": "FidelGlobal",
                 "email": "fidel@fidelitservices.com",
                 "contact_no": "",
                 "address": {
                            "line_1": "Test",
                            "line_2": "",
                            "city": "Pune",
                            "state_code":            "3",
                            "country_code": "1",
                            "postal_code": "422003"
                            }
                 }
 }
 */

public class LoginResponse {

    public static class StoreDetails{
        @SerializedName("id")
        private String id = null;

        @SerializedName("first_name")
        private String first_name = null;

        @SerializedName("last_name")
        private String last_name = null;

        @SerializedName("shop_name")
        private String shop_name = null;

        @SerializedName("email")
        private String email = null;

        @SerializedName("contact_no")
        private String contact_no = null;

        @SerializedName("address")
        private Address address =null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }
    public static class Address{
        @SerializedName("line_1")
        private String line_1 = null;

        @SerializedName("line_2")
        private String line_2 = null;

        @SerializedName("city")
        private String city = null;

        @SerializedName("state_code")
        private String state_code = null;

        @SerializedName("country_code")
        private String country_code = null;

        @SerializedName("postal_code")
        private String postal_code = null;

        public String getLine_1() {
            return line_1;
        }

        public void setLine_1(String line_1) {
            this.line_1 = line_1;
        }

        public String getLine_2() {
            return line_2;
        }

        public void setLine_2(String line_2) {
            this.line_2 = line_2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState_code() {
            return state_code;
        }

        public void setState_code(String state_code) {
            this.state_code = state_code;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public void setPostal_code(String postal_code) {
            this.postal_code = postal_code;
        }
    }

    @SerializedName("api_key")
     private String api_key=null;

    @SerializedName("api_secret")
     private String api_secret=null;

    @SerializedName("DriverId")
     private String DriverId=null;

    @SerializedName("storeDetails")
     private StoreDetails storeDetails = null;

    @SerializedName("Message")
    private String message=null;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public StoreDetails getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(StoreDetails storeDetails) {
        this.storeDetails = storeDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {

        String str = "api_key: "+api_key+" DriverId: "+DriverId+ " store.id: "+storeDetails.id+" address.city: "+storeDetails.address.city;
        return str;
    }
}
