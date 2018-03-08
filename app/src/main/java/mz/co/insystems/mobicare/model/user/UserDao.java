package mz.co.insystems.mobicare.model.user;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 11/6/2017.
 */

public interface UserDao extends Dao<User, Integer>{

    boolean authenticate(User user) throws SQLException;

    public User authenticateUser(User user) throws SQLException;

    public void deleteAllUser() throws SQLException;
}
