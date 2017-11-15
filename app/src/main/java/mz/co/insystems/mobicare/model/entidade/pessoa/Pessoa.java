package mz.co.insystems.mobicare.model.entidade.pessoa;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;
import mz.co.insystems.mobicare.model.entidade.contacto.Contact;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
public class Pessoa extends BaseVO {
    public static final String COLUMN_NAME 	        = "name";
    public static final String COLUMN_SURNAME       = "surname";
    public static final String COLUMN_CONTACT_ID 	= "contact_id";
    public static final String COLUMN_ENDERECO_ID 	= "endereco_id";

    private static final long serialVersionUID = 1L;

    private long id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String surname;
    @DatabaseField(columnName = COLUMN_CONTACT_ID, foreign = true, foreignAutoRefresh = true)
    private Contact contact;
    @DatabaseField(columnName = COLUMN_ENDERECO_ID, foreign = true, foreignAutoRefresh = true)
    private Endereco endereco;

    @Bindable
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        notifyPropertyChanged(BR.endereco);
    }

    public Pessoa() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        notifyPropertyChanged(BR.surname);
    }

    @Bindable
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        notifyPropertyChanged(BR.contact);
    }
}
