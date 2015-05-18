package com.meritnation.mnframework.application.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.meritnation.mnframework.modules.account.model.data.LoginTableRow;

import java.sql.SQLException;

/**
 * Created by Hukum Singh on 18/5/15.
 */
public class FrameworkORMDatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "meritnation-db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<LoginTableRow, String> LoginTableDao = null;
    public FrameworkORMDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, LoginTableRow.class);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(FrameworkORMDatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<LoginTableRow, String> getLoginTableDao() throws SQLException {
        if (LoginTableDao == null) {
            LoginTableDao = getDao(LoginTableRow.class);
        }
        return LoginTableDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        LoginTableDao = null;
    }
}
