package mz.co.insystems.mobicare.model.entidade.user;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Voloide Tamele on 10/2/2017.
 */
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao, Serializable {
    protected UserDaoImpl(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }


    @Override
    public boolean authenticate(User user) throws SQLException {
        return this.queryBuilder().where()
                .eq(User.COLUMN_USER_NAME, user.getUserName())
                .and()
                .eq(User.COLUMN_PASSWORD, user.getPassword()).queryForFirst() != null ? true : false;
    }

    @Override
    public CreateOrUpdateStatus jsonGenerateAndSave(JSONObject userJSON, boolean isFarmacia) throws SQLException, JSONException {
        CreateOrUpdateStatus status = this.createOrUpdate(User.convertFromJSON(userJSON, isFarmacia));
        return status;
    }
}
