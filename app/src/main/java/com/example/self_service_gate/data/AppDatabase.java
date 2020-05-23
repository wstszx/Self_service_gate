package com.example.self_service_gate.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

//@Database(entities = {GardenPlanting.class, Plant.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

//    public static AppDatabase getInstance(Context context) {
//        if (instance == null) {
//            synchronized (AppDatabase.class) {
//                instance = buildDatabase(context);
//            }
//        }
//        return instance;
//    }
}
