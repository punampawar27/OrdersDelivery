package com.tech20.mobiledelivery.models.Entities;


public class EntityLogin implements IEntity {

    private String storeId=null;
    private String username=null;
    private String pass=null;
    private String fireBaseRegKey =null;
    private String deviceId =null;
    private boolean isSessionUpdated =false;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFireBaseRegKey() {
        return fireBaseRegKey;
    }

    public void setFireBaseRegKey(String fireBaseRegKey) {
        this.fireBaseRegKey = fireBaseRegKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean getIsSessionUpdated() {
        return isSessionUpdated;
    }

    public void setIsSessionUpdated(boolean isSessionUpdated) {
        this.isSessionUpdated = isSessionUpdated;
    }
}
