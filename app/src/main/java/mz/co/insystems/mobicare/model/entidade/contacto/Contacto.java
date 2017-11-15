package mz.co.insystems.mobicare.model.entidade.contacto;

import android.databinding.Bindable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;

/**
 * Created by voloide on 9/15/16.
 */
public class Contacto extends BaseVO {

    public static final String TABLE_NAME_CONTACT			    = "contacto";
    public static final String COLUMN_CONTACT_ID 			    = "id";
    public static final String COLUMN_CONTACT_EMAIL 			= "email";
    public static final String COLUMN_CONTACT_PHONE_NUMBER_MAIN = "mainMobileNumber";
    public static final String COLUMN_CONTACT_PHONE_NUMBER_AUX 	= "auxMobileNumber";

    private static final long serialVersionUID = 1L;

    private long id;
    private String email;
    private String mainMobileNumber;
    private String auxMobileNumber;


    public Contacto(long id, String email, String mainMobileNumber, String auxMobileNumber) {
        this.id = id;
        this.email = email;
        this.mainMobileNumber = mainMobileNumber;
        this.auxMobileNumber = auxMobileNumber;
    }

    public Contacto(){}

    public Contacto(long id) {
        this.id = id;
    }

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException {
        Contacto contacto = new Contacto();
        contacto.setId(jsonObject.getInt(Contacto.COLUMN_CONTACT_ID));
        contacto.setEmail(jsonObject.getString(Contacto.COLUMN_CONTACT_EMAIL));
        contacto.setMainMobileNumber(jsonObject.getString(Contacto.COLUMN_CONTACT_PHONE_NUMBER_MAIN));
        contacto.setAuxMobileNumber(jsonObject.getString(Contacto.COLUMN_CONTACT_PHONE_NUMBER_AUX));
        return contacto;
    }

    @Override
    public JSONObject genarateJsonObject() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(COLUMN_CONTACT_ID, this.getId());
        object.put(COLUMN_CONTACT_EMAIL, this.getEmail());
        object.put(COLUMN_CONTACT_PHONE_NUMBER_MAIN, this.getMainMobileNumber());
        object.put(COLUMN_CONTACT_PHONE_NUMBER_AUX, this.getAuxMobileNumber());
        return object;
    }
}
