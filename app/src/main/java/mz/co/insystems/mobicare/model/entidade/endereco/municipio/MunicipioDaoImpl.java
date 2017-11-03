package mz.co.insystems.mobicare.model.entidade.endereco.municipio;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/3/2017.
 */
public class MunicipioDaoImpl extends BaseDaoImpl<Municipio, Integer> implements MunicipioDao {
    protected MunicipioDaoImpl(Class<Municipio> dataClass) throws SQLException {
        super(dataClass);
    }

    protected MunicipioDaoImpl(ConnectionSource connectionSource, Class<Municipio> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected MunicipioDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Municipio> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
