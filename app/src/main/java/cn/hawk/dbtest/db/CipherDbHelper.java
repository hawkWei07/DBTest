package cn.hawk.dbtest.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import net.sqlcipher.database.SQLiteDatabase;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.hawk.dbtest.model.User;

/**
 * Created by Stay on 29/10/15.
 * Powered by www.stay4it.com
 */
public class CipherDbHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名字
     */
    private static final String DB_NAME = "IMModel.db";
    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 7;
    /**
     * 用来存放Dao的地图
     */
    private Map<String, Dao> daos = new HashMap<>();

    private static CipherDbHelper instance;


    public CipherDbHelper(Context context, String password) {
        super(context, DB_NAME, password, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    /**
     * 获取单例
     *
     * @param context
     * @return
     */
    public static synchronized CipherDbHelper getHelper(Context context, String password) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (CipherDbHelper.class) {
                if (instance == null) {
                    SQLiteDatabase.loadLibs(context);
                    instance = new CipherDbHelper(context, password);
                }
            }
        }
        return instance;
    }

    /**
     * 通过类来获得指定的Dao
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
