package mz.co.insystems.mobicare.model.farmacia.servicos;

import android.databinding.Bindable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.base.json.JsonParseble;
import mz.co.insystems.mobicare.model.contacto.Contacto;
import mz.co.insystems.mobicare.model.endereco.Endereco;
import mz.co.insystems.mobicare.model.farmacia.Farmacia;
import mz.co.insystems.mobicare.model.search.Searchble;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@DatabaseTable(tableName = Servico.TABLE_NAME_SERVICO, daoClass = ServicoDaoImpl.class)
public class Servico extends BaseVO implements JsonParseble<Servico>, Searchble {
    public static final String TABLE_NAME_SERVICO			                        = "servico";
    public static final String COLUMN_SERVICO_ID 			                        = "id";
    public static final String COLUMN_SERVICO_DESIGNACAO			                = "designacao";
    public static final String COLUMN_SERVICO_DESCRICAO			                    = "descricao";
    public static final String COLUMN_SERVICO_ESTADO			                    = "estado";
    public static final String COLUMN_FARMACIA_ID			                        = "farmacia_id";




    @DatabaseField(columnName = COLUMN_SERVICO_ID, id = true, generatedId = false)
    private long id;
    @DatabaseField(columnName = COLUMN_SERVICO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_SERVICO_DESCRICAO)
    private String descricao;
    @DatabaseField
    private int estado;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = Servico.COLUMN_FARMACIA_ID)
    private Farmacia farmacia;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] logo;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] image;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Servico() {
    }

    public long getId() {
        return id;
    }

    @Override
    public Endereco getEndereco() {
        throw new RuntimeException("Metodo nao aplicavel");
    }

    @Override
    public Contacto getContacto() {
        throw new RuntimeException("Metodo nao aplicavel");
    }

    @Override
    public Farmacia getFarmacia() {
        return this.farmacia;
    }

    @Override
    public int getDisponibilidade() {
        throw new RuntimeException("Metodo nao aplicavel");
    }

    public void setId(long id) {
        this.id = id;
    }
    @Bindable
    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    @Bindable
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Bindable
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Bindable
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
        notifyPropertyChanged(BR.logo);
    }

    @Bindable
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }


    @Override
    public JSONObject toJsonObject() throws JsonProcessingException, JSONException {
        JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(this));
        return jsonObject;
    }

    @Override
    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    @Override
    public Servico fromJson(String jsonData) throws IOException {
        return objectMapper.readValue(jsonData, Servico.class);
    }

    @Override
    public Servico fromJsonObject(JSONObject response) throws IOException {
        return objectMapper.readValue(String.valueOf(response), Servico.class);
    }
}
