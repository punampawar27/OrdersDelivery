package com.tech20.mobiledelivery.database.dataentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.tech20.mobiledelivery.helpers.Constants;

/*
"storeDetails": {
        "id": 5141,
        "first_name": "FidelGlobal",
        "last_name": "",
        "shop_name": "FidelGlobal",
        "email": "fidel@fidelitservices.com",
        "contact_no": "0204865256",
        "address": {
            "line_1": "Test",
            "line_2": "",
            "city": "Pune",
            "state_code": "3",
            "country_code": "1",
            "postal_code": "422003                                            "
        }
    }
 */

@Entity(tableName = Constants.DatabaseConstants.TABLE_STORE.TABLE_NAME_STORE,
        indices = {@Index(value = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_STORE_ID, unique = true)})
public class DbModelStore implements Parcelable {

    public DbModelStore(){}
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.DatabaseConstants.COLUMN_NAME_AUTOINCREMENT_ID)
    private long id=0;


    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_STORE_ID)
    private Integer store_id=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_SHOP_NAME)
    private String shop_name=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_EMAIL)
    private String email=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_CONTACT_NO)
    private String contact_no=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_ADDRESS_LINE_1)
    private String address_line_1=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_ADDRESS_LINE_2)
    private String address_line_2=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_CITY)
    private String city=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_STATE_CODE)
    private String state_code=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_COUNTRY_CODE)
    private String country_code=null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_POSTAL_CODE)
    private String postal_code=null;

    protected DbModelStore(Parcel in) {
        id = in.readLong();
        if (in.readByte() == 0) {
            store_id = null;
        } else {
            store_id = in.readInt();
        }
        shop_name = in.readString();
        email = in.readString();
        contact_no = in.readString();
        address_line_1 = in.readString();
        address_line_2 = in.readString();
        city = in.readString();
        state_code = in.readString();
        country_code = in.readString();
        postal_code = in.readString();
    }

    public static final Creator<DbModelStore> CREATOR = new Creator<DbModelStore>() {
        @Override
        public DbModelStore createFromParcel(Parcel in) {
            return new DbModelStore(in);
        }

        @Override
        public DbModelStore[] newArray(int size) {
            return new DbModelStore[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
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

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        if (store_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(store_id);
        }
        dest.writeString(shop_name);
        dest.writeString(email);
        dest.writeString(contact_no);
        dest.writeString(address_line_1);
        dest.writeString(address_line_2);
        dest.writeString(city);
        dest.writeString(state_code);
        dest.writeString(country_code);
        dest.writeString(postal_code);
    }
}
