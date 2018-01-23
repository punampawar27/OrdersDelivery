package com.tech20.mobiledelivery.database.dataentities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.tech20.mobiledelivery.helpers.Constants;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = Constants.DatabaseConstants.TABLE_NOTES.TABLE_NAME_NOTES,
indices = @Index(value = Constants.DatabaseConstants.TABLE_NOTES.COLUMN_NAME_ORDERDELIVERYNOTESID , unique = true),
        foreignKeys = {@ForeignKey(entity = DbModelOrders.class,
                parentColumns = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ORDER_ID,
                childColumns = Constants.DatabaseConstants.TABLE_NOTES.COLUMN_NAME_ORDERID,
                onDelete = CASCADE)})

public class DbModelNotes implements Parcelable{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_NOTES.COLUMN_NAME_ORDERDELIVERYNOTESID)
    private String OrderDeliveryNotesId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_NOTES.COLUMN_NAME_ORDERID)
    private String OrderId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_NOTES.COLUMN_NAME_NOTE)
    private String Note = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_NOTES.COLUMN_NAME_DATECREATED)
    private String DateCreated = null;


    public DbModelNotes(){}
    protected DbModelNotes(Parcel in) {

        OrderDeliveryNotesId = in.readString();
        OrderId = in.readString();
        Note = in.readString();
        DateCreated = in.readString();
    }

    public static final Creator<DbModelNotes> CREATOR = new Creator<DbModelNotes>() {
        @Override
        public DbModelNotes createFromParcel(Parcel in) {
            return new DbModelNotes(in);
        }

        @Override
        public DbModelNotes[] newArray(int size) {
            return new DbModelNotes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(OrderDeliveryNotesId);
        dest.writeString(OrderId);
        dest.writeString(Note);
        dest.writeString(DateCreated);
    }

    public String getOrderDeliveryNotesId() {
        return OrderDeliveryNotesId;
    }

    public void setOrderDeliveryNotesId(@NonNull String orderDeliveryNotesId) {
        OrderDeliveryNotesId = orderDeliveryNotesId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }
}
