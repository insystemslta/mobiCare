package mz.co.insystems.mobicare.model.user;

import android.databinding.Bindable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.farmacia.Farmacia;
import mz.co.insystems.mobicare.model.pessoa.Pessoa;
import mz.co.insystems.mobicare.util.Utilities;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
@DatabaseTable(tableName = User.TABLE_NAME, daoClass = UserDaoImpl.class)
public class User extends BaseVO {
    public static final String TABLE_NAME           = "user";
    public static final String COLUMN_ID 			= "id";
    public static final String COLUMN_USER_NAME		= "user_name";
    public static final String COLUMN_PASSWORD 		= "password";
    public static final String COLUMN_ESTADO 		= "estado";
    public static final String COLUMN_PESSOA_ID		= "pessoa_id";
    public static final String COLUMN_FARMACIA_ID 	= "farmacia_id";

    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_ID, id = true, generatedId = false)
    private int id;
    @DatabaseField(columnName = COLUMN_USER_NAME, dataType = DataType.STRING)
    private String userName;
    @DatabaseField
    private String password;
    @DatabaseField(columnName = COLUMN_ESTADO)
    private int estado;

    @DatabaseField(columnName = COLUMN_PESSOA_ID, foreign = true, foreignAutoRefresh = true)
    private Pessoa pessoa;

    @DatabaseField(columnName = COLUMN_FARMACIA_ID, foreign = true, foreignAutoRefresh = true)
    private Farmacia farmacia;


    public User(int id) {
        this.id = id;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = Utilities.MD5Crypt(password);
    }

    public User(JSONObject response) throws JSONException {
        this.convertVoFromJSON(response);
    }

    public boolean isActive(){
        return this.estado == 1;
    }

    public User() {}

    @Bindable
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    @Override
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {

        this.setId(jsonObject.getInt(COLUMN_ID));
        this.setUserName(jsonObject.getString(User.COLUMN_USER_NAME));
        this.setNotCryptedPassword(jsonObject.getString(User.COLUMN_PASSWORD));
        if (jsonObject.has(Pessoa.TABLE_NAME)) {
            this.setPessoa(new Pessoa(jsonObject.getJSONObject(Pessoa.TABLE_NAME)));
        }
        if (jsonObject.has(Farmacia.TABLE_NAME_FARMACIA)) this.setFarmacia( new Farmacia(jsonObject.getJSONObject(Farmacia.TABLE_NAME_FARMACIA)));

    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject userJsonObject = new JSONObject();

        userJsonObject.put(COLUMN_ID,           this.getId());
        userJsonObject.put(COLUMN_USER_NAME,    this.getUserName());
        userJsonObject.put(COLUMN_PASSWORD,     this.getPassword());
        userJsonObject.put(COLUMN_ESTADO,       this.getEstado());

        if (this.getPessoa() != null)   userJsonObject.put(Pessoa.TABLE_NAME,               this.getPessoa().generateJsonObject());
        if (this.getFarmacia() != null) userJsonObject.put(Farmacia.TABLE_NAME_FARMACIA,    this.getFarmacia().generateJsonObject());
        return userJsonObject;
    }
}

