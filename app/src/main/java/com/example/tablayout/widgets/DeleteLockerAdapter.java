package com.example.tablayout.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout.R;
import com.example.tablayout.locker.edit_locker.EditLocker;

import java.util.ArrayList;
import java.util.List;

public class DeleteLockerAdapter extends RecyclerView.Adapter<DeleteLockerAdapter.ViewHolder> {
    private List<EditLocker> listRemoveLocker = new ArrayList<>();
    private DeleteLockerAdapterClickEvent clickEvent;

    public interface DeleteLockerAdapterClickEvent{
        void onCheck(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view) {
            super(view);
        }

        public void onBind(EditLocker editLocker, View.OnClickListener onClickListener){

        }
    }

    @NonNull
    @Override
    public DeleteLockerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_remove_locker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return listRemoveLocker.size();
    }
}
