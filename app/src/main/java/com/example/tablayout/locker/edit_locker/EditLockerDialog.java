package com.example.tablayout.locker.edit_locker;

import android.app.Activity;
import android.content.Context;
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

enum LockerIdStatus {
    INVALID,
    MAX,
    EXIST,
    VALID
}

enum LockerBLEAddress {
    INVALID,
    EXIST,
    VALID
}


public class EditLockerDialog extends DialogFragment {
    private String mTitle;
    private String mNegative;
    private String mPositive;

    private TextView txtTitle, txtCancel, txtLockerIDMessage, txtBleAddressMessage;
    private EditText edtLockerID, edtBleAddress;
    private Button btnOk;

    private EditLocker itemEditLocker;
    private LockerViewModel lockerViewModel;

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

    private void setUpMap(View view) {
        txtTitle = view.findViewById(R.id.txtTitle);
        edtLockerID = view.findViewById(R.id.edt_locker_id);
        txtLockerIDMessage = view.findViewById(R.id.txt_locker_message);
        edtBleAddress = view.findViewById(R.id.edt_ble_address);
        txtBleAddressMessage = view.findViewById(R.id.txt_address_message);
        txtCancel = view.findViewById(R.id.txtCancel);
        btnOk = view.findViewById(R.id.btnOk);
    }

    private void setUpClicks() {
        txtTitle.setText(mTitle);
        txtCancel.setText(mNegative);
        btnOk.setText(mPositive);

        btnOk.setOnClickListener(v -> {
            confirmLocker(itemEditLocker);
        });

        txtCancel.setOnClickListener(v -> {
            dismiss();
        });
    }

    private void confirmLocker(EditLocker itemEditLocker) {
        validateID(itemEditLocker);
        validateAddress(itemEditLocker);
        setUpLockerIDMessage(itemEditLocker);
        setUpBLEAddressMessage(itemEditLocker);
        if (itemEditLocker.getValidate()) {
            lockerViewModel.addLocker(itemEditLocker);
            dismiss();
        }
    }

    private void setUpLockerID(EditLocker itemEditLocker) {
        int lockerID = itemEditLocker.getLockerID();
        if (lockerID == 0) {
            edtLockerID.setText("");
        } else {
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

    private void setUpBLEAddress(EditLocker itemEditLocker) {
        edtBleAddress.setText(itemEditLocker.getBLEAddress());
        edtBleAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                itemEditLocker.setBLEAddress(edtBleAddress.getText().toString());
            }
        });
        edtBleAddress.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }


    private void setUpLockerIDMessage(EditLocker itemEditLocker) {
        String message = itemEditLocker.getLockerIDMessage();
        if (message.isEmpty()) {
            txtLockerIDMessage.setText("");
            txtLockerIDMessage.setVisibility(View.INVISIBLE);
        } else {
            txtLockerIDMessage.setText(message);
            txtLockerIDMessage.setVisibility(View.VISIBLE);
        }
    }

    private void setUpBLEAddressMessage(EditLocker itemEditLocker) {
        String message = itemEditLocker.getBLEMessage();
        if (message.isEmpty()) {
            txtBleAddressMessage.setText("");
            txtBleAddressMessage.setVisibility(View.INVISIBLE);
        } else {
            txtBleAddressMessage.setText(message);
            txtBleAddressMessage.setVisibility(View.VISIBLE);
        }
    }

    private boolean isRegisterID(int id) {
        List<EditLocker> listEditLocker = new ArrayList<>();
        for (int index = 0; index < listEditLocker.size(); index++) {
            EditLocker editLocker = listEditLocker.get(index);
            if (editLocker.getLockerID() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean isRegisterAddress(String address) {
        List<EditLocker> listEditLocker = new ArrayList<>();
        for (int index = 0; index < listEditLocker.size(); index++) {
            EditLocker editLocker = listEditLocker.get(index);
            if (editLocker.getBLEAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }
        return false;
    }

    private EditLocker validateID(EditLocker itemEditLocker) {
        String id = edtLockerID.getText().toString();
        if (!id.isEmpty()) {
            int lockerID = Integer.parseInt(id);
            switch (getLockerIdStatus(lockerID)) {
                case VALID:
                    itemEditLocker.setLockerID(lockerID);
                    itemEditLocker.setLockerIDMessage("");
                    itemEditLocker.setValidate(true);
                    break;
                case MAX:
                    itemEditLocker.setLockerIDMessage(getString(R.string.full_of_locker));
                    itemEditLocker.setValidate(false);
                    break;
                case EXIST:
                    itemEditLocker.setLockerIDMessage(getString(R.string.locker_is_registered));
                    itemEditLocker.setValidate(false);
                    break;
                case INVALID:
                    itemEditLocker.setLockerIDMessage(getString(R.string.locker_id_is_too_large));
                    itemEditLocker.setValidate(false);
                    break;
                default:
                    break;
            }
        } else {
            itemEditLocker.setLockerIDMessage(getString(R.string.locker_id_is_too_large));
            itemEditLocker.setValidate(false);
        }
        return itemEditLocker;
    }

    private EditLocker validateAddress(EditLocker itemEditLocker) {
        String bleAddress = edtBleAddress.getText().toString();
        switch (getLLockerBLEAddress(bleAddress)) {
            case INVALID:
                itemEditLocker.setBLEMessage(getString(R.string.ble_address_is_hex_number));
                itemEditLocker.setValidate(false);
                break;
            case EXIST:
                itemEditLocker.setBLEMessage(getString(R.string.ble_address_is_registered));
                itemEditLocker.setValidate(false);
                break;
            case VALID:
                itemEditLocker.setBLEMessage("");
                itemEditLocker.setValidate(true);
                itemEditLocker.setBLEAddress(bleAddress);
                break;
            default:
                break;
        }
        return itemEditLocker;
    }

    private LockerIdStatus getLockerIdStatus(int id) {
        if (id > 1000 || id < 0) return LockerIdStatus.INVALID;
        if (id == 1000) return LockerIdStatus.MAX;
        if (isRegisterID(id)) return LockerIdStatus.EXIST;
        return LockerIdStatus.VALID;
    }

    private LockerBLEAddress getLLockerBLEAddress(String bleAddress) {
        if (bleAddress.isEmpty() || bleAddress.length() < 12) return LockerBLEAddress.INVALID;
        if (isRegisterAddress(bleAddress)) return LockerBLEAddress.EXIST;
        return LockerBLEAddress.VALID;
    }


}
