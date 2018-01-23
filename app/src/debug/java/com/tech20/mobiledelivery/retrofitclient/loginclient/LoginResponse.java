package com.tech20.mobiledelivery.retrofitclient.loginclient;

import com.google.gson.annotations.SerializedName;

/**
 *  {
 "DriverId": 0,
 "api_key": "72d43a3e-5619-4093-90e8-ee4d62d6d8dd",
 "api_secret": "D9D8B8230DA3E7A27EDA7C5ADB89F03A",
 "storeDetails": {
 "id": 6630,
 "shop_name": "Cannapos",
 "email": "cannapos@cannapos.com",
 "contact_no": "45654565",
 "address": {
 "line_1": "Cannapos address",
 "line_2": "",
 "city": "cannapos",
 "state_code": "1",
 "country_code": "1",
 "postal_code": "41214121                                          "
 }
 },
 "address": null,
 "driverDetails": {
 "DriverId": 386,
 "FirstName": "pank",
 "LastName": "zan",
 "MobileNumber": "98749874",
 "device_id": "863854037854631922616973a11ae5eFMUSAYMZQCBMEMDU",
 "LicenceNo": "1234512234",
 "UserName": "pankz",
 "Password": "",
 "ClientId": 6630,
 "isDeleted": false,
 "RouteId": 0,
 "VehicleNumber": null,
 "isAvailable": false,
 "SessionId": "ABC",
 "AddressLine1": "address adress",
 "AddressLine2": "",
 "City": "Pune",
 "State": "maharastr",
 "ZipCode": 123451234,
 "Country": "India"
 },
 "MobileNumber": null,
 "device_id": null,
 "LicenceNo": null,
 "VehicleNumber": null,
 "UserName": null,
 "RouteId": 0,
 "AddressLine1": null,
 "AddressLine2": null,
 "City": null,
 "State": null,
 "ZipCode": 0,
 "Country": null
 }
 */

public class LoginResponse {

    public static class StoreDetails{
        @SerializedName("id")
        private String id = null;


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

    public static class DriverDetails{

       /* "driverDetails": {
            "DriverId": 386,
                    "FirstName": "pank",
                    "LastName": "zan",
                    "MobileNumber": "98749874",
                    "device_id": "863854037854631922616973a11ae5eFMUSAYMZQCBMEMDU",
                    "LicenceNo": "1234512234",
                    "UserName": "pankz",
                    "Password": "",
                    "ClientId": 6630,
                    "isDeleted": false,
                    "RouteId": 0,
                    "VehicleNumber": null,
                    "isAvailable": false,
                    "SessionId": "ABC",
                    "AddressLine1": "address adress",
                    "AddressLine2": "",
                    "City": "Pune",
                    "State": "maharastr",
                    "ZipCode": 123451234,
                    "Country": "India"
        }*/

        @SerializedName("FirstName")
        private String firstName = null;

        @SerializedName("LastName")
        private String lastName = null;

        @SerializedName("MobileNumber")
        private String mobileNumber = null;

        @SerializedName("UserName")
        private String userName = null;

        @SerializedName("DriverId")
        private String driverId = null;

        @SerializedName("City")
        private String city = null;

        @SerializedName("State")
        private String state = null;

        @SerializedName("ZipCode")
        private String zipcode = null;

        @SerializedName("Country")
        private String country = null;

        @SerializedName("LicenceNo")
        private String licenceNo = null;

        @SerializedName("VehicleNumber")
        private String vehicleNumber = null;

        @SerializedName("AddressLine1")
        private String addressLine1 = null;

        @SerializedName("AddressLine2")
        private String addressLine2 = null;


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLicenceNo() {
            return licenceNo;
        }

        public void setLicenceNo(String licenceNo) {
            this.licenceNo = licenceNo;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }
    }

    @SerializedName("api_key")
     private String api_key=null;

    @SerializedName("api_secret")
     private String api_secret=null;

    @SerializedName("storeDetails")
     private StoreDetails storeDetails = null;

    @SerializedName("driverDetails")
    private DriverDetails driverDetails = null;

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

    public DriverDetails getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetails driverDetails) {
        this.driverDetails = driverDetails;
    }

    @Override
    public String toString() {

        String str = "api_key: "+api_key+" DriverId: "+driverDetails.driverId+ " store.id: "+storeDetails.id+" address.city: "+storeDetails.address.city;
        return str;
    }
}
