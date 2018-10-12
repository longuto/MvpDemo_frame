package com.longuto.androidtemplet.database;

import android.content.Context;

import com.longuto.androidtemplet.greendao.db.DaoMaster;
import com.longuto.androidtemplet.greendao.db.DaoSession;
import com.longuto.androidtemplet.greendao.db.LogcollectionInfoDao;
import com.longuto.androidtemplet.model.greendao.LogcollectionInfo;
import com.longuto.androidtemplet.util.LogUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Author by yltang3,
 * Date on 2018/8/30.
 * PS: 日志上传数据库
 */
public class LogcollectionDaoUtil {

    public DBManager mDBManager;

    public LogcollectionDaoUtil(Context context) {
        mDBManager = DBManager.getInstance(context);
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertCategory(LogcollectionInfo user) {
        DaoMaster daoMaster = new DaoMaster(mDBManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LogcollectionInfoDao userDao = daoSession.getLogcollectionInfoDao();
        long insert = userDao.insert(user);
        LogUtils.db(LogcollectionDaoUtil.class.getSimpleName() + "插入一条数据的id:" + insert);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertCategoryList(List<LogcollectionInfo> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(mDBManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LogcollectionInfoDao userDao = daoSession.getLogcollectionInfoDao();
        userDao.insertOrReplaceInTx(users);
        LogUtils.db(LogcollectionDaoUtil.class.getSimpleName() + "插入数据集大小：" + users.size());
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteCategory(LogcollectionInfo user) {
        DaoMaster daoMaster = new DaoMaster(mDBManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LogcollectionInfoDao userDao = daoSession.getLogcollectionInfoDao();
        userDao.delete(user);
        LogUtils.db(LogcollectionDaoUtil.class.getSimpleName() + "删除一条数据");
    }

    /**
     * 删除所有记录
     */
    public void deleteAllCategory(){
        DaoMaster daoMaster = new DaoMaster(mDBManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LogcollectionInfoDao categoryEntityDao = daoSession.getLogcollectionInfoDao();
        categoryEntityDao.deleteAll();
        LogUtils.db(LogcollectionDaoUtil.class.getSimpleName() + "删除所有的数据");
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateCategory(LogcollectionInfo user) {
        DaoMaster daoMaster = new DaoMaster(mDBManager.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LogcollectionInfoDao userDao = daoSession.getLogcollectionInfoDao();
        userDao.update(user);
        LogUtils.db(LogcollectionDaoUtil.class.getSimpleName() + "更新一条数据");
    }

    /**
     * 查询用户列表
     */
    public List<LogcollectionInfo> queryCategoryList() {
        DaoMaster daoMaster = new DaoMaster(mDBManager.getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LogcollectionInfoDao userDao = daoSession.getLogcollectionInfoDao();
        QueryBuilder<LogcollectionInfo> qb = userDao.queryBuilder();
        List<LogcollectionInfo> list = qb.orderAsc(LogcollectionInfoDao.Properties.Time).list();
        LogUtils.db(LogcollectionDaoUtil.class.getSimpleName() + "查询数据集合大小：" + list.size());
        return list;
    }
}
