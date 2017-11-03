package mz.co.insystems.mobicare.model.entidade.user;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/2/2017.
 */
interface UserDao extends Dao<User, Integer> {

    public boolean authenticate(User user) throws SQLException;

}
