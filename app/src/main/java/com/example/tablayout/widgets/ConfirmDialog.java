package com.example.tablayout.widgets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tablayout.R;

import java.util.EventListener;

public class ConfirmDialog extends DialogFragment {
    private String mTitle;
    private String mMessage;
    private String mNegative;
    private String mPositive;
    private EventListener listener;

    public ConfirmDialog(@NonNull String mTitle, @NonNull String mMessage, @NonNull String mNegative, @NonNull String mPositive) {
        this.mTitle = mTitle;
        this.mMessage = mMessage;
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
        View view = inflater.inflate(R.layout.dialog_confirm, container);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtMessage= view.findViewById(R.id.txtMessage);
        TextView txtCancel= view.findViewById(R.id.txtCancel);
        Button btnOk= view.findViewById(R.id.btnOk);

        txtTitle.setText(mTitle);
        txtMessage.setText(mMessage);
        txtCancel.setText(mNegative);
        btnOk.setText(mPositive);

        btnOk.setOnClickListener(v -> {
            if(listener != null){
                listener.onConfirmed();
            }
            dismiss();
        });

        txtCancel.setOnClickListener(v -> {
            if(listener != null){
                listener.onClosed();
            }
            dismiss();
        });
        return view;
    }

    public interface EventListener{
        void onConfirmed();
        void onClosed();
    }
}
