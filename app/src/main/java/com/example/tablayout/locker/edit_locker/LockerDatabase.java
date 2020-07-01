package com.example.tablayout.locker.edit_locker;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {EditLocker.class}, version = 1, exportSchema = false)
public abstract class LockerDatabase extends RoomDatabase {
    public abstract LockerDAO lockerDAO();

    private static volatile LockerDatabase INSTANCE;

    private static final int NUMBER_OFF_THREADS = 2;

    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OFF_THREADS);

    static LockerDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (LockerDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LockerDatabase.class, "edit_locker")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}