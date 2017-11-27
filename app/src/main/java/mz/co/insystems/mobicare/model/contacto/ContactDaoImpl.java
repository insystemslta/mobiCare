package mz.co.insystems.mobicare.model.contacto;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/3/2017.
 */
public class ContactDaoImpl extends BaseDaoImpl<Contacto, Integer> implements ContactDao {
    public ContactDaoImpl(Class<Contacto> dataClass) throws SQLException {
        super(dataClass);
    }

    public ContactDaoImpl(ConnectionSource connectionSource, Class<Contacto> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ContactDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Contacto> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
