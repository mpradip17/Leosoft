package com.kotlintest.app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kotlintest.app.database.dao.UserDao;
import com.kotlintest.app.model.Sample;

@Database(entities =
        {Sample.class
        }, version = 1)/*version = AppConstants.DatabaseVersion, exportSchema = false)*/
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();

    private static final String DATABASE_NAME ="_Room" + "_db";
    private static volatile AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
                synchronized (AppDatabase.class) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, AppDatabase.DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .build();
                    }
            }
        }
        return sInstance;
    }


    public static void destroyInstance() {
        sInstance = null;
    }




    public abstract UserDao userDao();


}