package mz.co.insystems.mobicare.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.model.entidade.contacto.ContactDao;
import mz.co.insystems.mobicare.model.entidade.contacto.Contacto;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;
import mz.co.insystems.mobicare.model.entidade.endereco.EnderecoDao;
import mz.co.insystems.mobicare.model.entidade.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.entidade.endereco.bairro.BairroDao;
import mz.co.insystems.mobicare.model.entidade.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.entidade.endereco.distrito.DistritoDao;
import mz.co.insystems.mobicare.model.entidade.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.entidade.endereco.municipio.MunicipioDao;
import mz.co.insystems.mobicare.model.entidade.endereco.postoadministrativo.PostoAdministrativo;
import mz.co.insystems.mobicare.model.entidade.endereco.postoadministrativo.PostoAdministrativoDao;
import mz.co.insystems.mobicare.model.entidade.endereco.provincia.Provincia;
import mz.co.insystems.mobicare.model.entidade.endereco.provincia.ProvinciaDao;
import mz.co.insystems.mobicare.model.entidade.farmacia.Farmacia;
import mz.co.insystems.mobicare.model.entidade.farmacia.FarmaciaDao;
import mz.co.insystems.mobicare.model.entidade.pessoa.Pessoa;
import mz.co.insystems.mobicare.model.entidade.pessoa.PessoaDao;
import mz.co.insystems.mobicare.model.entidade.user.User;
import mz.co.insystems.mobicare.model.entidade.user.UserDao;

/**
 * Created by Voloide Tamele on 9/29/2017.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "mobicare.db";
    private static final int    DATABASE_VERSION = 1;

    private static DatabaseHelper sDatabaseHelper;

    private UserDao mUserDao;
    private PessoaDao pessoaDao;
    private FarmaciaDao farmaciaDao;
    private EnderecoDao enderecoDao;
    private ContactDao contactDao;

    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;
    private MunicipioDao municipioDao;
    private BairroDao bairroDao;
    private PostoAdministrativoDao postoAdministrativoDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    public static DatabaseHelper getInstance() {
        return sDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Pessoa.class);
            TableUtils.createTable(connectionSource, Farmacia.class);
            TableUtils.createTable(connectionSource, Endereco.class);
            TableUtils.createTable(connectionSource, Contacto.class);

            TableUtils.createTable(connectionSource, Provincia.class);
            TableUtils.createTable(connectionSource, Distrito.class);
            TableUtils.createTable(connectionSource, Municipio.class);
            TableUtils.createTable(connectionSource, PostoAdministrativo.class);
            TableUtils.createTable(connectionSource, Bairro.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //TableUtils.dropTable(connectionSource, Vehicle.class, true);

        //onCreate(database, connectionSource);
    }


    public UserDao getUserDao() throws java.sql.SQLException {
        if (mUserDao == null){
            mUserDao = getDao(User.class);
        }
        return mUserDao;
    }



    public PessoaDao getPessoaDao() throws java.sql.SQLException {
        if (pessoaDao == null){
            pessoaDao = getDao(Pessoa.class);
        }
        return pessoaDao;
    }

    public FarmaciaDao getFarmaciaDao()throws java.sql.SQLException {
        if (farmaciaDao == null){
            farmaciaDao = getDao(Farmacia.class);
        }  return farmaciaDao;
    }

    public EnderecoDao getEnderecoDao()throws java.sql.SQLException {
        if (enderecoDao == null){
            enderecoDao = getDao(Endereco.class);
        }   return enderecoDao;
    }

    public ContactDao getContactDao()throws java.sql.SQLException{
        if (contactDao == null){
            contactDao = getDao(Contacto.class);
        }  return contactDao;
    }

    public ProvinciaDao getProvinciaDao() throws SQLException {
        if (provinciaDao == null){
            provinciaDao = getDao(Provincia.class);
        }
        return provinciaDao;
    }

    public DistritoDao getDistritoDao() throws SQLException {
        if (distritoDao == null){
            distritoDao = getDao(Distrito.class);
        }
        return distritoDao;
    }

    public MunicipioDao getMunicipioDao() throws SQLException {
        if (municipioDao == null){
            municipioDao = getDao(Municipio.class);
        }
        return municipioDao;
    }

    public BairroDao getBairroDao() throws SQLException {
        if (bairroDao == null){
            bairroDao = getDao(Bairro.class);
        }
        return bairroDao;
    }

    public PostoAdministrativoDao getPostoAdministrativoDao() throws SQLException {
        if (postoAdministrativoDao == null){
            postoAdministrativoDao = getDao(PostoAdministrativo.class);
        }
        return postoAdministrativoDao;
    }
}
