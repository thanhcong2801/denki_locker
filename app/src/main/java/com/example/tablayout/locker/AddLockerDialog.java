package com.example.tablayout.locker;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout.R;
import com.example.tablayout.locker.edit_locker.EditLocker;
import com.example.tablayout.locker.edit_locker.EditLockerDialog;
import com.example.tablayout.locker.edit_locker.LockerViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddLockerDialog extends DialogFragment {
    private String mTitle;
    private String mTextAdd;
    private String mNegative;
    private String mPositive;
    private EventListener listener;
    private List<EditLocker> editLockerList = new ArrayList<>();

    private LockerViewModel viewModel;
    private AddLockerAdapter mAdapter;

    public AddLockerDialog(@NonNull String mTitle, @NonNull String mTextAdd, @NonNull String mNegative, @NonNull String mPositive) {
        this.mTitle = mTitle;
        this.mTextAdd = mTextAdd;
        this.mNegative = mNegative;
        this.mPositive = mPositive;
    }

    public EventListener getListener() {
        return listener;
    }

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_locker, container);
        setUpOnclick(view);
        setUpRecyclerView(view);

//        final Observer<EditLocker> observer = new Observer<EditLocker>() {
//            @Override
//            public void onChanged(EditLocker editLocker) {
//
//            }
//
//        }

        return view;
    }


    public interface EventListener{
        void onConfirm();
        void onClosed();
    }

    private void setUpOnclick(View view){
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtAdd = view.findViewById(R.id.txtAdd);
        TextView txtCancel = view.findViewById(R.id.txtCancel);
        Button btnOk = view.findViewById(R.id.btnOk);

        txtTitle.setText(mTitle);
        txtAdd.setText(mTextAdd);
        txtCancel.setText(mNegative);
        btnOk.setText(mPositive);

        btnOk.setOnClickListener(v -> {
            if(listener != null){
                listener.onConfirm();
            }
            dismiss();
        });

        txtCancel.setOnClickListener(v -> {
            if(listener != null){
                listener.onClosed();
            }
            dismiss();
        });

        txtAdd.setOnClickListener(v -> {
            addLocker();
        });
    }

    private void setUpRecyclerView(View view){
        RecyclerView rcLockerList = view.findViewById(R.id.rcl_add_locker);
        rcLockerList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcLockerList.setHasFixedSize(true);
        getViewModel().getLockers().observe(getViewLifecycleOwner(), new Observer<List<EditLocker>>() {
            @Override
            public void onChanged(List<EditLocker> editLockers) {
                mAdapter = new AddLockerAdapter(editLockers, (int position) -> {

                });
                rcLockerList.setAdapter(mAdapter);
            }
        });
    }

    private LockerViewModel getViewModel(){
        if(viewModel == null){
            viewModel = new ViewModelProvider(this).get(LockerViewModel.class);
        }
        return viewModel;
    }


    private void addLocker(){
        EditLockerDialog dialog = new EditLockerDialog(
                getString(R.string.input_locker_title),
                getString(R.string.cancel),
                getString(R.string.ok),
                new EditLocker(0, "")
        );
        dialog.show(getParentFragmentManager(), "dialog_edit_locker");
    }
}
