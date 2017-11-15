package mz.co.insystems.mobicare.model.entidade.pessoa;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.co.insystems.mobicare.model.entidade.farmaco.Farmaco;

/**
 * Created by Voloide Tamele on 10/3/2017.
 */
public class PessoaDaoImpl extends BaseDaoImpl<Pessoa, Integer> implements PessoaDao {
    protected PessoaDaoImpl(Class<Pessoa> dataClass) throws SQLException {
        super(dataClass);
    }

    protected PessoaDaoImpl(ConnectionSource connectionSource, Class<Pessoa> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected PessoaDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Pessoa> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
