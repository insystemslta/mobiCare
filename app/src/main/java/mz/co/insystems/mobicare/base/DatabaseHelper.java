package mz.co.insystems.mobicare.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import mz.co.insystems.trackingservice.model.command.Command;
import mz.co.insystems.trackingservice.model.command.CommandDao;
import mz.co.insystems.trackingservice.model.command.CommandPart;
import mz.co.insystems.trackingservice.model.command.CommandPartDao;
import mz.co.insystems.trackingservice.model.command.CommandResponse;
import mz.co.insystems.trackingservice.model.command.CommandResponseDao;
import mz.co.insystems.trackingservice.model.pessoas.Contact;
import mz.co.insystems.trackingservice.model.pessoas.ContactDao;
import mz.co.insystems.trackingservice.model.pessoas.owner.Owner;
import mz.co.insystems.trackingservice.model.pessoas.owner.OwnerDao;
import mz.co.insystems.trackingservice.model.pessoas.user.User;
import mz.co.insystems.trackingservice.model.pessoas.user.UserDAO;
import mz.co.insystems.trackingservice.model.plan.Plan;
import mz.co.insystems.trackingservice.model.plan.PlanDao;
import mz.co.insystems.trackingservice.model.plan.items.PlanItem;
import mz.co.insystems.trackingservice.model.plan.items.PlanItemDao;
import mz.co.insystems.trackingservice.model.vehicle.UserVehicle;
import mz.co.insystems.trackingservice.model.vehicle.Vehicle;
import mz.co.insystems.trackingservice.model.vehicle.VehicleDao;

/**
 * Created by Voloide Tamele on 9/29/2017.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "mobicare.db";
    private static final int    DATABASE_VERSION = 1;

    private static DatabaseHelper sDatabaseHelper;

    //private VehicleDao mVehicleDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance() {
        return sDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //TableUtils.createTable(connectionSource, Vehicle.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //TableUtils.dropTable(connectionSource, Vehicle.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    public VehicleDao getVehicleDao() throws SQLException {

        if (mVehicleDao == null){
            mVehicleDao = getDao(Vehicle.class);
        }
        return mVehicleDao;
    }
    */

}
