package com.hua.testpro2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hua on 2019/3/6.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,
                        DatabaseErrorHandler errorHandler) {
        super(context, name, null, version, errorHandler);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version, int minimumSupportedVersion, DatabaseErrorHandler errorHandler) {
        super(context, name, null, version, minimumSupportedVersion, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE user_info(\n" +
                "   name TEXT,\n" +
                "   age INTEGER,\n" +
                "   man BLOB,\n" +
                "   score REAL,\n" +
                "   _id integer PRIMARY KEY AUTOINCREMENT NOT NULL \n" +
                ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("@@@hua", "onUpgrade oldVersion = " + oldVersion + ", newVersion = " + newVersion);
    }
}
