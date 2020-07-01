package com.example.tablayout.locker.edit_locker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tablayout.locker.AddLockerAdapter;

import java.util.List;

public class LockerViewModel extends AndroidViewModel implements AddLockerAdapter.ItemLockerListener {
    private LockerRepository mLockerRepository;
    private LiveData<List<EditLocker>> lockers;

    public LiveData<List<EditLocker>> getLockers() {
        return lockers;
    }

    public LockerViewModel(Application application){
        super(application);
        mLockerRepository = new LockerRepository(application);
        lockers = mLockerRepository.getAllLockers();
    }

    void insert(EditLocker editLocker){
        mLockerRepository.insert(editLocker);
    }



    @Override
    public void onCheck(int position) {

    }
}
