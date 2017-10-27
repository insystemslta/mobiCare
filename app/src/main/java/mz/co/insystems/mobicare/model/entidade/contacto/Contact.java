package mz.co.insystems.mobicare.model.entidade.contacto;

import android.databinding.Bindable;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;

/**
 * Created by voloide on 9/15/16.
 */
public class Contact extends BaseVO {

    public static final String TABLE_NAME_CONTACT			        = "contact";
    public static final String COLUMN_CONTACT_ID 			    = "id";
    public static final String COLUMN_CONTACT_EMAIL 			= "email";
    public static final String COLUMN_CONTACT_PHONE_NUMBER_MAIN = "mainMobileNumber";
    public static final String COLUMN_CONTACT_PHONE_NUMBER_AUX 	= "auxMobileNumber";

    private static final long serialVersionUID = 1L;

    private long id;
    private String email;
    private String mainMobileNumber;
    private String auxMobileNumber;


    public Contact(long id, String email, String mainMobileNumber, String auxMobileNumber) {
        this.id = id;
        this.email = email;
        this.mainMobileNumber = mainMobileNumber;
        this.auxMobileNumber = auxMobileNumber;
    }

    public Contact(){}

    public Contact(long id) {
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
}
