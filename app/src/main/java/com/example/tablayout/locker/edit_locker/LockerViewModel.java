package com.example.tablayout.locker.edit_locker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tablayout.locker.AddLockerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LockerViewModel extends AndroidViewModel implements AddLockerAdapter.ItemLockerListener {
    List<EditLocker> listLocker = new ArrayList<>();

    private LiveData<List<EditLocker>> lockers;
    private LockerRepository mLockerRepository;
    private MutableLiveData<List<EditLocker>> listAddLocker = new MutableLiveData<>();

    public LiveData<List<EditLocker>> getLocker() {
        return lockers;
    }

    public MutableLiveData<List<EditLocker>> getListAddLocker() {
        return listAddLocker;
    }

    public LockerViewModel(Application application) {
        super(application);
        mLockerRepository = new LockerRepository(application);
        lockers = mLockerRepository.getAllLocker();
    }

    void insert(EditLocker editLocker) {
        mLockerRepository.insert(editLocker);
    }



    public void addLocker(EditLocker locker) {
        listLocker.add(locker);
        this.listAddLocker.setValue(listLocker);
    }

    @Override
    public void onCheck(int position) {
        listAddLocker.getValue();
    }
}
