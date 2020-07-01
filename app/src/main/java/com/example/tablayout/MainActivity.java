package com.example.tablayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tablayout.locker.AddLockerDialog;
import com.example.tablayout.widgets.DeleteLockerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnFab, btnAdd, btnRemove;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();
        setStatus();
        addLocker();
        removeLocker();
    }

    private void setStatus(){
        btnFab.setOnClickListener(v -> {
            Boolean isClose = btnAdd.getVisibility() == View.INVISIBLE;
            if(isClose) {
                open();
            }else{
                close();
            }
        });

    }

    private void addLocker(){
        btnAdd.setOnClickListener(v -> {
            AddLockerDialog dialog = new AddLockerDialog(
                    getString(R.string.add_locker_title),
                    getString(R.string.add),
                    getString(R.string.cancel),
                    getString(R.string.register)
            );
            dialog.setListener(new AddLockerDialog.EventListener() {
                @Override
                public void onConfirm() {

                }

                @Override
                public void onClosed() {

                }
            });
            dialog.show(getSupportFragmentManager(), "dialog_register_log");
        });
    }

    private void removeLocker(){
        btnRemove.setOnClickListener(v -> {
            DeleteLockerDialog dialog = new DeleteLockerDialog(
                    getString(R.string.delete_locker_title),
                    getString(R.string.cancel),
                    getString(R.string.delete)
            );
            dialog.setListener(new DeleteLockerDialog.EventListener() {
                @Override
                public void onConfirm() {

                }

                @Override
                public void onClose() {

                }
            });
            dialog.show(getSupportFragmentManager(), "dialog_remove_log");
        });
    }


    private void map(){
        btnFab = findViewById(R.id.menu);
        btnAdd = findViewById(R.id.add);
        btnRemove = findViewById(R.id.remove);
    }

    private void open(){
        btnFab.setImageResource(R.drawable.ic_close_18dp);
        btnAdd.setVisibility(View.VISIBLE);
        btnRemove.setVisibility(View.VISIBLE);
    }

    private void close(){
        btnFab.setImageResource(R.drawable.ic_mode_edit_18dp);
        btnAdd.setVisibility(View.INVISIBLE);
        btnRemove.setVisibility(View.INVISIBLE);
    }



}
