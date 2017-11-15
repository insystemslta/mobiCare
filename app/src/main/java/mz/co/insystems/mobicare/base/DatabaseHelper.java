package mz.co.insystems.mobicare.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import mz.co.insystems.mobicare.model.entidade.contacto.ContactDao;
import mz.co.insystems.mobicare.model.entidade.endereco.EnderecoDao;
import mz.co.insystems.mobicare.model.entidade.farmacia.FarmaciaDao;
import mz.co.insystems.mobicare.model.entidade.pessoa.Pessoa;
import mz.co.insystems.mobicare.model.entidade.contacto.Contacto;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;
import mz.co.insystems.mobicare.model.entidade.farmacia.Farmacia;
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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //TableUtils.dropTable(connectionSource, Vehicle.class, true);

        onCreate(database, connectionSource);
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
}
