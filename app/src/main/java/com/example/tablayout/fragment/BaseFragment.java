package com.example.tablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView() != null){
            onViewInnit(getView());
        }
    }

    protected void onViewInnit(@NonNull View view){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(onGetLayoutId() != 0){
            View view = inflater.from(getContext()).inflate(onGetLayoutId(), null, false);
            if(view != null){
                return view;
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected int onGetLayoutId(){
        return 0;
    }

    protected <T extends View> T findViewId(@IdRes int id){
        if(getView() != null){
            return getView().findViewById(id);
        }
        return null;
    }

    protected void setUpOnClick(@IdRes int id, @NonNull View.OnClickListener listener){
        View view = findViewId(id);
        if(view != null){
            view.setOnClickListener(listener);
        }
    }

}
