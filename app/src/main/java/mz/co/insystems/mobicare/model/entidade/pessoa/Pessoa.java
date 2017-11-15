package mz.co.insystems.mobicare.model.entidade.pessoa;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.contacto.Contacto;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
public class Pessoa extends BaseVO {
    public static final String TABLE_NAME 	        = "pessoa";
    public static final String COLUMN_ID 	        = "id";
    public static final String COLUMN_NAME 	        = "nome";
    public static final String COLUMN_SURNAME       = "apelido";
    public static final String COLUMN_CONTACT_ID 	= "contacto_id";
    public static final String COLUMN_ENDERECO_ID 	= "endereco_id";

    private static final long serialVersionUID = 1L;

    private long id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String surname;
    @DatabaseField(columnName = COLUMN_CONTACT_ID, foreign = true, foreignAutoRefresh = true)
    private Contacto contacto;
    @DatabaseField(columnName = COLUMN_ENDERECO_ID, foreign = true, foreignAutoRefresh = true)
    private Endereco endereco;

    public Pessoa(long id, String name, String surname, Contacto contacto, Endereco endereco) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contacto = contacto;
        this.endereco = endereco;
    }

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
    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
        notifyPropertyChanged(BR.contacto);
    }


    @Override
    public BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException {
        return null;
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();

            jsonObject.put(COLUMN_ID,       this.getId());
            jsonObject.put(COLUMN_NAME,     this.getName());
            jsonObject.put(COLUMN_SURNAME,  this.getSurname());
            jsonObject.put(Contacto.TABLE_NAME_CONTACT, this.getContacto().generateJsonObject());
            jsonObject.put(Endereco.TABLE_NAME_ENDERECO, this.getEndereco().generateJsonObject());

        return jsonObject;
    }
}
