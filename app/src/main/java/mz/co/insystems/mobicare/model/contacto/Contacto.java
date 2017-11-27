package mz.co.insystems.mobicare.model.contacto;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Contacto.TABLE_NAME_CONTACT, daoClass = ContactDaoImpl.class)
public class Contacto extends BaseVO {

    public static final String TABLE_NAME_CONTACT			    = "contacto";
    public static final String COLUMN_CONTACT_ID 			    = "id";
    public static final String COLUMN_CONTACT_EMAIL 			= "email";
    public static final String COLUMN_CONTACT_PHONE_NUMBER_MAIN = "mainMobileNumber";
    public static final String COLUMN_CONTACT_PHONE_NUMBER_AUX 	= "auxMobileNumber";

    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_CONTACT_ID, id = true, generatedId = false)
    private int id;
    @DatabaseField
    private String email;
    @DatabaseField
    private String mainMobileNumber;
    @DatabaseField
    private String auxMobileNumber;


    public Contacto(int id, String email, String mainMobileNumber, String auxMobileNumber) {
        this.id = id;
        this.email = email;
        this.mainMobileNumber = mainMobileNumber;
        this.auxMobileNumber = auxMobileNumber;
    }

    public Contacto(){}

    public Contacto(int id) {
        this.id = id;
    }

    public Contacto(JSONObject jsonObject) throws JSONException {
        this.convertVoFromJSON(jsonObject);
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getMainMobileNumber() {
        return mainMobileNumber;
    }

    public void setMainMobileNumber(String mainMobileNumber) {
        this.mainMobileNumber = mainMobileNumber;
        notifyPropertyChanged(BR.mainMobileNumber);
    }

    @Bindable
    public String getAuxMobileNumber() {
        return auxMobileNumber;
    }

    public void setAuxMobileNumber(String auxMobileNumber) {
        this.auxMobileNumber = auxMobileNumber;
        notifyPropertyChanged(BR.auxMobileNumber);
    }

    @Override
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {

        this.setId(jsonObject.getInt(Contacto.COLUMN_CONTACT_ID));
        this.setEmail(jsonObject.getString(Contacto.COLUMN_CONTACT_EMAIL));
        this.setMainMobileNumber(jsonObject.getString(Contacto.COLUMN_CONTACT_PHONE_NUMBER_MAIN));
        this.setAuxMobileNumber(jsonObject.getString(Contacto.COLUMN_CONTACT_PHONE_NUMBER_AUX));
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(COLUMN_CONTACT_ID, this.getId());
        object.put(COLUMN_CONTACT_EMAIL, this.getEmail());
        object.put(COLUMN_CONTACT_PHONE_NUMBER_MAIN, this.getMainMobileNumber());
        object.put(COLUMN_CONTACT_PHONE_NUMBER_AUX, this.getAuxMobileNumber());
        return object;
    }
}
