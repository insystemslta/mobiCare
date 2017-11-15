package mz.co.insystems.mobicare;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import mz.co.insystems.mobicare.model.entidade.contacto.Contacto;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;
import mz.co.insystems.mobicare.model.entidade.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.entidade.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.entidade.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.entidade.endereco.postoadministrativo.PostoAdministrativo;
import mz.co.insystems.mobicare.model.entidade.endereco.provincia.Provincia;
import mz.co.insystems.mobicare.model.entidade.farmacia.Farmacia;
import mz.co.insystems.mobicare.model.entidade.pessoa.Pessoa;
import mz.co.insystems.mobicare.model.entidade.user.User;

/**
 * Created by Voloide Tamele on 11/15/2017.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {
            User.class,
            Pessoa.class,
            Farmacia.class,
            Endereco.class,
            Contacto.class,
            Provincia.class,
            Distrito.class,
            Municipio.class,
            PostoAdministrativo.class,
            Bairro.class
    };

    public static void main(String[] args) throws SQLException, IOException {

        // Provide the name of .txt file which you have already created and kept in res/raw directory
        writeConfigFile("ormlite_config.txt", classes);
    }
}
