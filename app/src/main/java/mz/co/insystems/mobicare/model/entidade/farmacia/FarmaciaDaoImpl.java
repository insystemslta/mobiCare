package mz.co.insystems.mobicare.model.entidade.farmacia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.co.insystems.mobicare.model.entidade.user.User;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
public class FarmaciaDaoImpl extends BaseDaoImpl<Farmacia, Integer> implements FarmaciaDao {
    protected FarmaciaDaoImpl(Class<Farmacia> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FarmaciaDaoImpl(ConnectionSource connectionSource, Class<Farmacia> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FarmaciaDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Farmacia> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

}
