package mz.co.insystems.mobicare.model.entidade.user;

import android.databinding.Bindable;
import android.location.Location;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.model.entidade.Pessoa;
import mz.co.insystems.mobicare.util.Utilities;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
@DatabaseTable(tableName = User.TABLE_NAME, daoClass = UserDAO.class)
public class User extends Pessoa{
    public static final String TABLE_NAME                       = "user";
    public static final String COLUMN_USER_ID 				    = "id";
    public static final String COLUMN_USER_LOGIN_NUMBER		    = "user_name";
    public static final String COLUMN_USER_PASSWORD 		    = "password";
    public static final String COLUMN_USER_STATE 			    = "state_id";
    public static final String COLUMN_USER_TYPE 			    = "user_type_id";

    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_USER_ID, id = true, generatedId = false)
    private long id;
    private String userName;
    private String password;
    private boolean active;
    private Location location;

    public User(long id) {
        this.id = id;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = Utilities.MD5Crypt(password);
    }

    public User() {}

    @Bindable
    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;

        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setCryptedPassword(String password){
        this.setPassword(password, true);
    }

    public void setNotCryptedPassword(String password){
        this.setPassword(password, false);
    }

    private void setPassword(String password, boolean isToCrypt) {
        if (isToCrypt)
            this.password = Utilities.MD5Crypt(password.trim());
        else
            this.password = password;
        notifyPropertyChanged(BR.password);


    }
}

