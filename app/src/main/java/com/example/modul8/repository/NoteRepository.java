package com.example.modul8.repository;

import android.app.Application;
import android.os.Parcelable;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.example.modul8.database.DAONote;
import com.example.modul8.database.Note;
import com.example.modul8.database.NoteRoomDB;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private final DAONote mDaoNotes;
    private final ExecutorService executorService;
    public NoteRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        NoteRoomDB db = NoteRoomDB.getDatabase(application);
        mDaoNotes = db.daoNote();
    }
    public LiveData<List<Note>> getAllNotes() {
        return mDaoNotes.getAllNotes();
    }
    public void insert(final Note note) {
        executorService.execute(() -> mDaoNotes.insert(note));
    }
    public void delete(final Note note){
        executorService.execute(() -> mDaoNotes.delete(note));
    }
    public void update(final Note note){
        executorService.execute(() -> mDaoNotes.update(note));
    }

}
