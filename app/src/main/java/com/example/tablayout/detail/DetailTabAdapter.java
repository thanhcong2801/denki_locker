package com.example.tablayout.detail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tablayout.MainActivity;
import com.example.tablayout.log.LogFragment;
import com.example.tablayout.reset.ResetFragment;
import com.example.tablayout.settings.SettingFragment;
import com.example.tablayout.status.StatusFragment;

public class DetailTabAdapter extends FragmentStateAdapter {
    private final static int FRAGMENT_SIZE = 4;

    public DetailTabAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

//    public DetailTabAdapter(DetailFragment detailFragment) {
//        super(detailFragment);
//    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new StatusFragment();
            case 1: return new LogFragment();
            case 2: return new SettingFragment();
            default: return new ResetFragment();
        }
    }

    @Override
    public int getItemCount() {
        return FRAGMENT_SIZE;
    }
}
