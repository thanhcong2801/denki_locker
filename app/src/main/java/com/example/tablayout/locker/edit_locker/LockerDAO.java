package com.example.tablayout.locker.edit_locker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LockerDAO {
    @Insert
    void insertLocker(EditLocker editLocker);

    @Query("Select locker_id, ble_address from edit_locker")
    LiveData<List<EditLocker>> getData();
}
