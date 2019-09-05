package com.aanadon.android.anadonc196.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
import com.aanadon.android.anadonc196.utilities.Converter_Date;

@Database(entities = {TermEntity.class, TermNoteEntity.class}, version = 2, exportSchema = false)
@TypeConverters(Converter_Date.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME    = "C196.db";

    //  Volatile == ONLY REFERENCED FROM MAIN MEMORY
    private static volatile AppDatabase _Instance;
    private static final Object _Lock = new Object();

    // TODO: 8/31/2019 ONE FOR EACH DAO INSTANCE
    public abstract TermDAO TermDAO();
    public abstract TermNoteDAO TermNoteDAO();

    public static AppDatabase getInstance(Context pContext) {
        if (null == _Instance) {
            synchronized (_Lock)    {
                if (null == _Instance)  {
                    _Instance = Room.databaseBuilder(pContext.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return _Instance;
    }
}
