package mz.co.insystems.mobicare.model.entidade.farmaco.grupofarmaco;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
public class GrupoFarmacoDaoImpl extends BaseDaoImpl<GrupoFarmaco, Integer> implements GrupoFarmacoDao {
    protected GrupoFarmacoDaoImpl(Class<GrupoFarmaco> dataClass) throws SQLException {
        super(dataClass);
    }

    protected GrupoFarmacoDaoImpl(ConnectionSource connectionSource, Class<GrupoFarmaco> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected GrupoFarmacoDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<GrupoFarmaco> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

}
