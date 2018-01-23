package com.tech20.mobiledelivery.database.dataentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.tech20.mobiledelivery.helpers.Constants;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = Constants.DatabaseConstants.TABLE_DRIVER.TABLE_NAME_DRIVER,
        indices = {@Index(value = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_DRIVER_ID,unique = true)},
        foreignKeys = {@ForeignKey( entity = DbModelStore.class,
                                    parentColumns = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_STORE_ID,
                                    childColumns = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_STORE_ID,
                                    onDelete = CASCADE)})
public class DbModelDriver implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.DatabaseConstants.COLUMN_NAME_AUTOINCREMENT_ID)
    private long uid=0;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_API_KEY)
    private String api_key = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_API_SECRET)
    private String api_secret = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_DRIVER_ID)
    private String driverId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_STORE_ID)
    private Integer store_id=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_FIRST_NAME)
    private String first_name = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_LAST_NAME)
    private String last_name = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_USERNAME)
    private String username = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_CITY)
    private String city = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_STATE)
    private String state = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_ZIPCODE)
    private String zipcode = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_Country)
    private String country = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_LICENCENO)
    private String licenceNo = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_VEHICLENUMBER)
    private String vehicleNumber = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_MOBILENUMBER)
    private String mobileNumber = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_ADDRESSLINE1)
    private String addressLine1 = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_ADDRESSLINE2)
    private String addressLine2 = null;


    public DbModelDriver(){}
    private DbModelDriver(Parcel in) {
        uid = in.readLong();
        api_key = in.readString();
        api_secret = in.readString();
        driverId = in.readString();
        if (in.readByte() == 0) {
            store_id = null;
        } else {
            store_id = in.readInt();
        }
        first_name = in.readString();
        last_name = in.readString();
        username = in.readString();
        city = in.readString();
        state = in.readString();
        zipcode = in.readString();
        country = in.readString();
        licenceNo = in.readString();
        vehicleNumber = in.readString();
        mobileNumber = in.readString();
        addressLine1 = in.readString();
        addressLine2 = in.readString();
    }

    public static final Creator<DbModelDriver> CREATOR = new Creator<DbModelDriver>() {
        @Override
        public DbModelDriver createFromParcel(Parcel in) {
            return new DbModelDriver(in);
        }

        @Override
        public DbModelDriver[] newArray(int size) {
            return new DbModelDriver[size];
        }
    };

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

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
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(uid);
        dest.writeString(api_key);
        dest.writeString(api_secret);
        dest.writeString(driverId);
        if (store_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(store_id);
        }
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(username);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipcode);
        dest.writeString(country);
        dest.writeString(licenceNo);
        dest.writeString(vehicleNumber);
        dest.writeString(mobileNumber);
        dest.writeString(addressLine1);
        dest.writeString(addressLine2);

    }
}
