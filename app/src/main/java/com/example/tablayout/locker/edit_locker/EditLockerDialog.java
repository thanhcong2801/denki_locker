package com.example.tablayout.locker.edit_locker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tablayout.R;
import com.example.tablayout.widgets.ConvertHelper;

import java.util.ArrayList;
import java.util.List;


public class EditLockerDialog extends DialogFragment {
    private String mTitle;
    private String mNegative;
    private String mPositive;

    private TextView txtTitle, txtCancel, txtLockerIDMessage, txtBleAddressMessage;
    private EditText edtLockerID, edtBleAddress;
    private Button btnOk;

    private EditLocker itemEditLocker;
    private LockerViewModel lockerViewModel;
//    private EditLockerDialogListener mListener;

    List<EditLocker> listItemLocker = new ArrayList<>();

    public EditLockerDialog(@NonNull String mTitle, @NonNull String mNegative, @NonNull String mPositive, EditLocker itemEditLocker) {
        this.mTitle = mTitle;
        this.mNegative = mNegative;
        this.mPositive = mPositive;
        this.itemEditLocker = itemEditLocker;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_locker, container);
        setUpMap(view);
        setUpClicks();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lockerViewModel = new ViewModelProvider(getActivity()).get(LockerViewModel.class);
    }

//    public interface EditLockerDialogListener{
//        void onDialogPositive(EditLockerDialog editLockerDialog);
//    }

//    @Override
//    public void onAttach(@NonNull Activity activity) {
//        super.onAttach(activity);
//        try{
//            mListener = (EditLockerDialogListener) activity;
//        }catch (ClassCastException e){
//            throw new ClassCastException(activity.toString() + "must implement EditLockerDialogListener" );
//        }
//    }

    private void setUpMap(View view) {
        txtTitle = view.findViewById(R.id.txtTitle);
        edtLockerID = view.findViewById(R.id.edt_locker_id);
        txtLockerIDMessage = view.findViewById(R.id.txt_locker_message);
        edtBleAddress = view.findViewById(R.id.edt_ble_address);
        txtBleAddressMessage = view.findViewById(R.id.txt_address_message);
        txtCancel = view.findViewById(R.id.txtCancel);
        btnOk = view.findViewById(R.id.btnOk);
    }

    private void setUpClicks(){
        txtTitle.setText(mTitle);
        txtCancel.setText(mNegative);
        btnOk.setText(mPositive);

        btnOk.setOnClickListener(v -> {
//            mListener.onDialogPositive(EditLockerDialog.this);
            insertData();
        });

        txtCancel.setOnClickListener(v -> {
            dismiss();
        });
    }

    private void insertData() {
        validateID();
        validateAddress();
        setUpLockerIDMessage(itemEditLocker);
        setUpBLEAddressMessage(itemEditLocker);

        if(itemEditLocker.getValidate()){
            listItemLocker.add(itemEditLocker);
            lockerViewModel.setListAddLocker(listItemLocker);
//            lockerViewModel.insert(new EditLocker(Integer.parseInt(edtLockerID.getText().toString()), edtBleAddress.getText().toString()));
        }
    }

    private void setUpLockerID(EditLocker itemEditLocker){
        int lockerID = itemEditLocker.getLockerID();
        if(lockerID == 0){
            edtLockerID.setText("");
        }else{
            edtLockerID.setText(ConvertHelper.numberToStringID(lockerID));
        }
        edtLockerID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String id = edtLockerID.getText().toString();
                int newID = Integer.parseInt(id);
                itemEditLocker.setLockerID(newID);
            }
        });
        edtLockerID.setImeOptions(EditorInfo.IME_ACTION_DONE);

    }

    private void setUpBLEAddress(EditLocker itemEditLocker){
        edtBleAddress.setText(itemEditLocker.getBLEMessage());
        edtBleAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String address = edtBleAddress.getText().toString();
                itemEditLocker.setBLEAddress(address);
            }
        });
        edtBleAddress.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }



    private void setUpLockerIDMessage(EditLocker itemEditLocker){
        String message = itemEditLocker.getLockerMessage();
        if(message.isEmpty()){
            txtLockerIDMessage.setText("");
            txtLockerIDMessage.setVisibility(View.INVISIBLE);
        }else{
            txtLockerIDMessage.setText(message);
            txtLockerIDMessage.setVisibility(View.VISIBLE);
        }
    }

    private void setUpBLEAddressMessage(EditLocker itemEditLocker){
        String message = itemEditLocker.getBLEMessage();
        if(message.isEmpty()){
            txtBleAddressMessage.setText("");
            txtBleAddressMessage.setVisibility(View.INVISIBLE);
        }else {
            txtBleAddressMessage.setText(message);
            txtBleAddressMessage.setVisibility(View.VISIBLE);
        }
    }

    private boolean isRegisterID(int id) {
        int lockerID = itemEditLocker.getLockerID();
        if (lockerID == id) {
            return true;
        }
        return false;
    }

    private boolean isRegisterAddress(String address) {
        String bleAddress = itemEditLocker.getBLEAddress();
        if (bleAddress.equalsIgnoreCase(address)) {
            return true;
        }
        return false;
    }

    private void validateID(){
        String id = edtLockerID.getText().toString();
        if(!id.isEmpty()){
            int lockerID = Integer.parseInt(id);
            if (getContext() != null) {
                if (lockerID > 1000 || lockerID < 0) {
                    String messageError = getContext().getString(R.string.locker_id_is_too_large);
                    itemEditLocker.setLockerMessage(messageError);
                    itemEditLocker.setValidate(false);
                } else {
                    if (isRegisterID(lockerID)) {
                        String messageError = getContext().getString(R.string.locker_is_registered);
                        itemEditLocker.setLockerMessage(messageError);
                        itemEditLocker.setValidate(false);
                    } else {
                        itemEditLocker.setLockerMessage("");
                        itemEditLocker.setValidate(true);
                    }
                }
            }
        }else{
            itemEditLocker.setLockerMessage(getString(R.string.locker_id_is_too_large));
        }

    }

    private void validateAddress(){
        String bleAddress = edtBleAddress.getText().toString();
        if(getContext() != null){
            if (bleAddress.isEmpty() || bleAddress.length() < 12) {
                String messageError = getContext().getString(R.string.ble_address_is_hex_number);
                itemEditLocker.setBLEMessage(messageError);
                itemEditLocker.setValidate(false);
            } else {
                if (isRegisterAddress(bleAddress)) {
                    String messageError = getContext().getString(R.string.ble_address_is_registered);
                    itemEditLocker.setBLEMessage(messageError);
                    itemEditLocker.setValidate(false);
                } else {
                    if (bleAddress.length() == 12) {
                        itemEditLocker.setBLEMessage("");
                        itemEditLocker.setValidate(true);
                        itemEditLocker.setBLEAddress(bleAddress);
                    }
                }
            }
        }
    }


}
