package com.models.mappers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.models.File;

import java.util.ArrayList;

/**
 * Created by Usuario on 19/04/2015.
 */
public class FileMapper {

    public FileMapper(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.getWritableDb();
    }

    public long insetFile(File file) {
        return dbManager.insert(file);
    }

    public ArrayList findAllFIlesByNote(int noteId){
        ArrayList<File> files = new ArrayList<File>();
        String selectQuery = "SELECT * FROM files WHERE note_id =" + noteId;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                File file = new File();
                file.setContentValues(cursor);
                files.add(file);
            } while (cursor.moveToFirst());
        }

        return files;
    }

    public void updateFile (File file){
        dbManager.update(file);
    }

    public void deleteFile(File file){
        dbManager.delete(file);
    }

    private DBManager dbManager;
    private SQLiteDatabase db;
}