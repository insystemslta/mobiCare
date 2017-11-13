package mz.co.insystems.mobicare.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

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




}
