package mz.co.insystems.mobicare.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.model.contacto.ContactDao;
import mz.co.insystems.mobicare.model.contacto.Contacto;
import mz.co.insystems.mobicare.model.endereco.Endereco;
import mz.co.insystems.mobicare.model.endereco.EnderecoDao;
import mz.co.insystems.mobicare.model.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.endereco.bairro.BairroDao;
import mz.co.insystems.mobicare.model.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.endereco.distrito.DistritoDao;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.endereco.municipio.MunicipioDao;
import mz.co.insystems.mobicare.model.endereco.postoadministrativo.PostoAdministrativo;
import mz.co.insystems.mobicare.model.endereco.postoadministrativo.PostoAdministrativoDao;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;
import mz.co.insystems.mobicare.model.endereco.provincia.ProvinciaDao;
import mz.co.insystems.mobicare.model.farmacia.Farmacia;
import mz.co.insystems.mobicare.model.farmacia.FarmaciaDao;
import mz.co.insystems.mobicare.model.farmacia.servicos.Servico;
import mz.co.insystems.mobicare.model.farmacia.servicos.ServicoDao;
import mz.co.insystems.mobicare.model.farmaco.Farmaco;
import mz.co.insystems.mobicare.model.farmaco.FarmacoDao;
import mz.co.insystems.mobicare.model.pessoa.Pessoa;
import mz.co.insystems.mobicare.model.pessoa.PessoaDao;
import mz.co.insystems.mobicare.model.search.RecentRearhDao;
import mz.co.insystems.mobicare.model.search.RecentSearch;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.model.user.UserDao;

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
    private FarmacoDao farmacoDao;
    private ServicoDao servicoDao;

    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;
    private MunicipioDao municipioDao;
    private BairroDao bairroDao;
    private PostoAdministrativoDao postoAdministrativoDao;
    private RecentRearhDao recentRearhDao;

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
            TableUtils.createTable(connectionSource, Servico.class);

            TableUtils.createTable(connectionSource, Provincia.class);
            TableUtils.createTable(connectionSource, Distrito.class);
            TableUtils.createTable(connectionSource, Municipio.class);
            TableUtils.createTable(connectionSource, PostoAdministrativo.class);
            TableUtils.createTable(connectionSource, Bairro.class);
            TableUtils.createTable(connectionSource, RecentSearch.class);

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

    public FarmacoDao getFarmacoDao() throws java.sql.SQLException {
        if (farmacoDao == null){
            farmacoDao = getDao(Farmaco.class);
        }
        return farmacoDao;
    }

    public ServicoDao getServicoDao() throws java.sql.SQLException {
        if (servicoDao == null){
            servicoDao = getDao(Servico.class);
        }
        return servicoDao;
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

    public RecentRearhDao getRecentRearhDao() throws SQLException {
        if (recentRearhDao == null){
            recentRearhDao = getDao(RecentSearch.class);
        }
        return recentRearhDao;
    }
}
