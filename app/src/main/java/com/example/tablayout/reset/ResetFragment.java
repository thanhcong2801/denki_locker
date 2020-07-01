package com.example.tablayout.reset;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.tablayout.R;
import com.example.tablayout.fragment.BaseFragment;
import com.example.tablayout.widgets.ConfirmDialog;

public class ResetFragment extends BaseFragment {
    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_reset;
    }

    @Override
    protected void onViewInnit(@NonNull View view) {
        super.onViewInnit(view);
        setUpOnClick(R.id.btnReset, v -> {
            ConfirmDialog dialog = new ConfirmDialog(
                    getString(R.string.confirm_dialog_title),
                    getString(R.string.confirm_dialog_message),
                    getString(R.string.confirm_dialog_btn_cancel),
                    getString(R.string.confirm_dialog_btn_reset)
            );
            dialog.setListener(new ConfirmDialog.EventListener() {
                @Override
                public void onConfirmed() {

                }

                @Override
                public void onClosed() {

                }
            });
            dialog.show(getParentFragmentManager(), "dialog_reset");
        });
    }
}
