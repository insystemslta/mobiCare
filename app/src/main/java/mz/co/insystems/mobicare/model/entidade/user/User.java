package mz.co.insystems.mobicare.model.entidade.user;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.Pessoa;
import mz.co.insystems.mobicare.model.entidade.farmacia.Farmacia;
import mz.co.insystems.mobicare.util.Utilities;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
@DatabaseTable(tableName = User.TABLE_NAME, daoClass = UserDao.class)
public class User extends BaseVO {
    public static final String TABLE_NAME           = "user";
    public static final String COLUMN_ID 			= "id";
    public static final String COLUMN_USER_NAME		= "user_name";
    public static final String COLUMN_PASSWORD 		= "password";
    public static final String COLUMN_ESTADO 		= "estado";
    public static final String COLUMN_TYPE 			= "user_type_id";
    public static final String COLUMN_PESSOA_ID			= "pessoa_id";
    public static final String COLUMN_FARMACIA_ID 			= "farmacia_id";

    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_ID, id = true, generatedId = false)
    private long id;
    @DatabaseField(columnName = COLUMN_USER_NAME)
    private String userName;
    @DatabaseField
    private String password;
    @DatabaseField(columnName = COLUMN_ESTADO)
    private boolean active;

    @DatabaseField(columnName = COLUMN_PESSOA_ID, foreign = true, foreignAutoRefresh = true)
    private Pessoa pessoa;

    @DatabaseField(columnName = COLUMN_FARMACIA_ID, foreign = true, foreignAutoRefresh = true)
    private Farmacia farmacia;


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
    @Bindable
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        notifyPropertyChanged(BR.pessoa);
    }
    @Bindable
    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
        notifyPropertyChanged(BR.farmacia);
    }

    public JSONObject genarateJsonObject() {
        JSONObject userJsonObject = new JSONObject();
        try {
            userJsonObject.put(User.COLUMN_USER_NAME, this.getUserName());
            userJsonObject.put(User.COLUMN_PASSWORD, this.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJsonObject;
    }

    public static User convertFromJSON(JSONObject userJson, boolean isFarmacia) throws JSONException {
        User user = new User();
        user.setId(userJson.getInt(COLUMN_ID));
        user.setUserName(userJson.getString(User.COLUMN_USER_NAME));
        user.setNotCryptedPassword(userJson.getString(User.COLUMN_PASSWORD));
        if (!isFarmacia) {
            user.setPessoa(Pessoa.convertFromJSON(userJson.getJSONObject(Pessoa.TABLE_NAME)));
        }else {
            user.setFarmacia(Farmacia.convertFromJSON(userJson.getJSONObject(Farmacia.TABLE_NAME_FARMACIA)));
        }
        return user;
    }
}

