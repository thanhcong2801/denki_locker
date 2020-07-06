package com.example.tablayout.locker.edit_locker;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "edit_locker")
public class EditLocker {

    private int checkID = -1;
    @PrimaryKey
    @ColumnInfo(name = "locker_id")
    private int mLockerID;

    @ColumnInfo(name = "ble_address")
    private String mBLEAddress;


    private String mLockerIDMessage = "";
    private String mBLEMessage = "";
    private Boolean isValidate = false;

    public EditLocker(int mLockerID, String mBLEAddress) {
        this.mLockerID = mLockerID;
        this.mBLEAddress = mBLEAddress;
    }

    public int getCheckID() {
        return checkID;
    }

    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }

    public int getLockerID() {
        return mLockerID;
    }

    public void setLockerID(int mLockerID) {
        this.mLockerID = mLockerID;
    }

    public String getLockerIDMessage() {
        return mLockerIDMessage;
    }

    public void setLockerIDMessage(String mLockerMessage) {
        this.mLockerIDMessage = mLockerMessage;
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
            if(getLockerIDMessage().isEmpty() && getBLEMessage().isEmpty()){
                 isValidate = true;
            }
        }else {
            isValidate = false;
        }

    }

}
