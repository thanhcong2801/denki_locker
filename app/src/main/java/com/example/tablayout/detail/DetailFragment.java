package com.example.tablayout.detail;

import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.example.tablayout.R;
import com.example.tablayout.fragment.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailFragment extends BaseFragment {

    private DetailTabAdapter detailTabAdapter;
    private ViewPager2 viewPager2;
    TabLayout tabLayout;

    private final static int STATUS_TAB = 0;
    private final static int LOG_TAB = 1;
    private final static int SETTING_TAB = 2;
    private final static int MAINTENANCE_TAB = 3;


    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_detail;
    }

    private void setUpViewPager(){
        viewPager2 = getView().findViewById(R.id.view_pager);
        tabLayout = getView().findViewById(R.id.tab_layout);
        detailTabAdapter = new DetailTabAdapter(this);
        viewPager2.setAdapter(detailTabAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case STATUS_TAB:
                    tab.setText(R.string.status_tab_title);
                    break;
                case LOG_TAB:
                    tab.setText(R.string.log_history_tab_title);
                    break;
                case SETTING_TAB:
                    tab.setText(R.string.setting_tab_title);
                    break;
                case MAINTENANCE_TAB:{
                    tab.setText(R.string.maintenance);
                    break;
                }
            }
        }).attach();
    }
}
