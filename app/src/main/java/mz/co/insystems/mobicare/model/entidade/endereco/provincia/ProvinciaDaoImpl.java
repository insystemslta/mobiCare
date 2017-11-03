package mz.co.insystems.mobicare.model.entidade.endereco.provincia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/3/2017.
 */
public class ProvinciaDaoImpl extends BaseDaoImpl<Provincia, Integer> implements ProvinciaDao {
    protected ProvinciaDaoImpl(Class<Provincia> dataClass) throws SQLException {
        super(dataClass);
    }

    protected ProvinciaDaoImpl(ConnectionSource connectionSource, Class<Provincia> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected ProvinciaDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Provincia> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
