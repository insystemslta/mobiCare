package mz.co.insystems.mobicare.model.entidade.farmacia;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.contacto.Contacto;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;
import mz.co.insystems.mobicare.model.entidade.farmacia.servicos.Servico;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
@DatabaseTable(tableName = Farmacia.TABLE_NAME_FARMACIA, daoClass = FarmaciaDao.class)
public class Farmacia extends BaseVO {
    public static final String TABLE_NAME_FARMACIA			                = "farmacia";
    public static final String COLUMN_FARMACIA_ID 			                = "id";
    public static final String COLUMN_FARMACIA_NOME			                = "nome";
    public static final String COLUMN_FARMACIA_ENDERECO 			        = "endereco_id";
    public static final String COLUMN_FARMACIA_CONTACTO 			        = "contacto_id";
    public static final String COLUMN_FARMACIA_ESTADO 			            = "estado";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_FARMACIA_ID, id = true, generatedId = false)
    private long id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private int estado;
    @DatabaseField(columnName = COLUMN_FARMACIA_ENDERECO, foreign = true, foreignAutoRefresh = true)
    private Endereco endereco;

    @DatabaseField(columnName = COLUMN_FARMACIA_CONTACTO, foreign = true, foreignAutoRefresh = true)
    private Contacto contacto;
    @ForeignCollectionField
    private List<Servico> servicos;
    public Farmacia(long id, String nome, int estado, Endereco endereco, Contacto contacto) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.endereco = endereco;
        this.contacto = contacto;
    }
    public Farmacia() {

    }
    @Override
    public String toString() {
        return "GrupoFarmaco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado=" + estado +
                ", endereco=" + endereco +
                ", contacto=" + contacto +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Bindable
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Bindable
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    @Bindable
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    @Bindable
    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }


    @Override
    public BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException {
        return null;
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject userJsonObject = new JSONObject();

        userJsonObject.put(COLUMN_FARMACIA_ID,      this.getId());
        userJsonObject.put(COLUMN_FARMACIA_NOME,    this.getNome());
        userJsonObject.put(COLUMN_FARMACIA_ESTADO,  this.getEstado());
        userJsonObject.put(Contacto.TABLE_NAME_CONTACT,     this.getContacto().generateJsonObject());
        userJsonObject.put(Endereco.TABLE_NAME_ENDERECO,    this.getEndereco().generateJsonObject());

        return userJsonObject;
    }

}
