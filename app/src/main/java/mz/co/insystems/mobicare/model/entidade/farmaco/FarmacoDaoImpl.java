package mz.co.insystems.mobicare.model.entidade.farmaco;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/3/2017.
 */
public class FarmacoDaoImpl extends BaseDaoImpl<Farmaco, Integer> implements FarmacoDao {
    protected FarmacoDaoImpl(Class<Farmaco> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FarmacoDaoImpl(ConnectionSource connectionSource, Class<Farmaco> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FarmacoDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Farmaco> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
