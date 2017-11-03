package mz.co.insystems.mobicare.model.entidade.contacto;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/3/2017.
 */
public class ContactDaoImpl extends BaseDaoImpl<Contact, Integer> implements ContactDao {
    protected ContactDaoImpl(Class<Contact> dataClass) throws SQLException {
        super(dataClass);
    }

    protected ContactDaoImpl(ConnectionSource connectionSource, Class<Contact> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected ContactDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Contact> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}