package mz.co.insystems.mobicare.model.entidade;

import android.databinding.Bindable;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.contacto.Contact;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
public class Pessoa extends BaseVO {
    public static final String COLUMN_PESSOA_NAME 	        = "name";
    public static final String COLUMN_PESSOA_SURNAME        = "surname";
    public static final String COLUMN_PESSOA_CONTACT_ID 	= "contact_id";

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String surname;
    private Contact contact;

    public Pessoa(long id, String name, String surname, Contact contact) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contact = contact;
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
