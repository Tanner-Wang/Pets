package com.example.administrator.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.administrator.pets.data.PetContract.SQL_CREATE_TABLE;
import static com.example.administrator.pets.data.PetContract.SQL_DELETE_TABLE;

/**
 * Created by Administrator on 2017/3/18.
 */

public final class PetDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pet.db";
    public static final int DATABASE_VERSION = 1;

    public PetDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
