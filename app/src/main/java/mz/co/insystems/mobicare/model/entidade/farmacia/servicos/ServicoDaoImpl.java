package mz.co.insystems.mobicare.model.entidade.farmacia.servicos;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
public class ServicoDaoImpl extends BaseDaoImpl<Servico, Integer> implements ServicoDao {
    protected ServicoDaoImpl(Class<Servico> dataClass) throws SQLException {
        super(dataClass);
    }

    protected ServicoDaoImpl(ConnectionSource connectionSource, Class<Servico> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected ServicoDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Servico> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
