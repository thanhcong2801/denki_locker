package com.example.tablayout.locker.edit_locker;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class LockerRepository {
    private LockerDAO mLockerDAO;
    private LiveData<List<EditLocker>> mAllLockers;

    public LockerRepository(Application application) {
        LockerDatabase lockerDatabase = LockerDatabase.getDatabase(application);
        mLockerDAO = lockerDatabase.lockerDAO();
        mAllLockers = mLockerDAO.getData();
    }

    LiveData<List<EditLocker>> getAllLocker(){
        return mAllLockers;

    }

    void insert(EditLocker editLocker){
        LockerDatabase.databaseExecutor.execute(() -> {
            mLockerDAO.insertLocker(editLocker);
        });
    }



}
