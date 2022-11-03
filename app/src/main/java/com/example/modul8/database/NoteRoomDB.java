package com.example.modul8.database;

import android.content.Context;
import android.os.Parcelable;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteRoomDB extends RoomDatabase {
    public abstract DAONote daoNote();

    private static volatile NoteRoomDB INSTANCE;
    public static NoteRoomDB getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (NoteRoomDB.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NoteRoomDB.class, "note_db").build();
            }
        }
        return INSTANCE;
    }
}
