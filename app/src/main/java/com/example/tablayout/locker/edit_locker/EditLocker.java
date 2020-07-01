package com.example.tablayout.locker.edit_locker;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "edit_locker")
public class EditLocker {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "locker_id")
    private int mLockerID;

    @NonNull
    @ColumnInfo(name = "ble_address")
    private String mBLEAddress;


    private String mLockerMessage = "";
    private String mBLEMessage = "";
    private Boolean isValidate = false;

    public EditLocker(int mLockerID, String mBLEAddress) {
        this.mLockerID = mLockerID;
        this.mBLEAddress = mBLEAddress;
    }

    public int getLockerID() {
        return mLockerID;
    }

    public void setLockerID(int mLockerID) {
        this.mLockerID = mLockerID;
    }

    public String getLockerMessage() {
        return mLockerMessage;
    }

    public void setLockerMessage(String mLockerMessage) {
        this.mLockerMessage = mLockerMessage;
    }

    public String getBLEAddress() {
        return mBLEAddress;
    }

    public void setBLEAddress(String mBLEAddress) {
        this.mBLEAddress = mBLEAddress;
    }

    public String getBLEMessage() {
        return mBLEMessage;
    }

    public void setBLEMessage(String mBLEMessage) {
        this.mBLEMessage = mBLEMessage;
    }

    public Boolean getValidate() {
        return isValidate;
    }

    public void setValidate(Boolean validate) {
        if(validate){
            if(getLockerMessage().isEmpty() && getBLEMessage().isEmpty()){
                 isValidate = true;
            }
        }else {
            isValidate = false;
        }

    }

}
