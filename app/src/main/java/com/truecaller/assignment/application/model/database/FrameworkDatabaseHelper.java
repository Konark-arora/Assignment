
package com.truecaller.assignment.application.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This is SQLiteOpenHelper class for whole app.
 */
public class FrameworkDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "truecaller-db";
    private static final int DATABASE_VERSION = 1;

    /**
     * This public constructor for app SQLiteOpenHelper class.
     * @param context
     */
    public FrameworkDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * will be called very first time when user will call getReadableDataBase or getWritableDatabase
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * This will be called whenever we will change database version. we should do alteration of tables here.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
