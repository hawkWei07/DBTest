package cn.hawk.dbtest.db;


import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import cn.hawk.dbtest.common.Constant;
import cn.hawk.dbtest.model.User;

/**
 * Created by Stay on 29/10/15.
 * Powered by www.stay4it.com
 */
public class UserDAO extends BaseDAO<User> {

    private CipherDbHelper helper;

    public UserDAO(Context context) {
        try {
            helper = CipherDbHelper.getHelper(context, Constant.DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dao<User, Integer> getDAO() throws SQLException {
        return helper.getDao(User.class);
    }
}
