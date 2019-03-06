package com.avfsoftware.pelinabeer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "movies";
    private static final int DB_VERSION = 1;

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MYMOVIES (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, poster TEXT, original_title TEXT, overview TEXT, release_date TEXT," +
                "vote_average TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertMovie (String title, String poster_path, String original_title, String overview,String release_date, String vote_average) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("poster", poster_path);
        contentValues.put("original_title", original_title);
        contentValues.put("overview", overview);
        contentValues.put("release_date", release_date);
        contentValues.put("vote_average", vote_average);
        db.insert("MYMOVIES", null, contentValues);
        return true;
    }
}
