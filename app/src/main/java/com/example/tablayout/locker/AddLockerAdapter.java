package com.example.tablayout.locker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout.R;
import com.example.tablayout.widgets.ConvertHelper;
import com.example.tablayout.locker.edit_locker.EditLocker;

import java.util.ArrayList;
import java.util.List;

public class AddLockerAdapter extends RecyclerView.Adapter<AddLockerAdapter.ViewHolder> {
    private List<EditLocker> listAddLocker;
    private ItemLockerListener itemLockerListener;

    public interface ItemLockerListener{
        void onCheck(int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(EditLocker editLocker, View.OnClickListener clickListener){
            TextView txtLockerID = itemView.findViewById(R.id.edt_locker_id);
            txtLockerID.setText(ConvertHelper.numberToStringID(editLocker.getLockerID()));
            TextView txtBLEAddress = itemView.findViewById(R.id.edt_ble_address);
            txtBLEAddress.setText(editLocker.getBLEAddress());
            if(clickListener != null){
                LinearLayout layout = itemView.findViewById(R.id.click_add_locker);
                layout.setOnClickListener(clickListener);
            }
        }

    }

    public AddLockerAdapter(List<EditLocker> listLocker, ItemLockerListener listener){
        this.listAddLocker = listLocker;
        this.itemLockerListener = listener;
    }

    @NonNull
    @Override
    public AddLockerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_locker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddLockerAdapter.ViewHolder holder, int position) {
        holder.bind(listAddLocker.get(position), v -> {
            if(itemLockerListener != null){
                itemLockerListener.onCheck(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAddLocker.size();
    }


}
