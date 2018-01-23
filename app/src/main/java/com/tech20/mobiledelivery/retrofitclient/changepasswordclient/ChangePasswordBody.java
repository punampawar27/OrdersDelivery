package com.tech20.mobiledelivery.retrofitclient.changepasswordclient;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fidel25 on 12/11/2017.
 */

public class ChangePasswordBody {

    @SerializedName("oldPassword")
    private String strOldPassword = null;

    @SerializedName("newPassword")
    private String strnewPassword = null;

    @SerializedName("driverId")
    private String strdriverId = null;

    public String getStrOldPassword() {
        return strOldPassword;
    }

    public void setStrOldPassword(String strOldPassword) {
        this.strOldPassword = strOldPassword;
    }

    public String getStrnewPassword() {
        return strnewPassword;
    }

    public void setStrnewPassword(String strnewPassword) {
        this.strnewPassword = strnewPassword;
    }

    public String getStrdriverId() {
        return strdriverId;
    }

    public void setStrdriverId(String strdriverId) {
        this.strdriverId = strdriverId;
    }
}
