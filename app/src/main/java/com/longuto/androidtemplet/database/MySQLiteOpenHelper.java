package com.longuto.androidtemplet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.longuto.androidtemplet.greendao.db.DaoMaster;
import com.longuto.androidtemplet.greendao.db.LogcollectionInfoDao;

/**
 * Author by yltang3,
 * Date on 2018/9/30.
 * PS: 数据库迁移，位置变换
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据迁移模块
        MigrationHelper.migrate(db,
                LogcollectionInfoDao.class);
    }
}
