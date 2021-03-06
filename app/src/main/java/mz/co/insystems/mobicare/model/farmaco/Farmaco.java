package mz.co.insystems.mobicare.model.farmaco;

import android.databinding.Bindable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import mz.co.insystems.mobicare.model.farmaco.grupofarmaco.GrupoFarmaco;
import mz.co.insystems.mobicare.model.search.Searchble;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@DatabaseTable(tableName = Farmaco.TABLE_NAME_FARMACO, daoClass = FarmacoDaoImpl.class)
public class Farmaco extends BaseVO implements JsonParseble<Farmaco>, Searchble {

    public static final String TABLE_NAME_FARMACO			        = "farmaco";
    public static final String COLUMN_FARMACO_ID 			        = "id";
    public static final String COLUMN_FARMACO_DESIGNACAO 			= "designacao";
    public static final String COLUMN_FARMACO_DISPONIBILIDADE       = "disponibilidade";
    public static final String COLUMN_FARMACO_PRECO 	            = "preco";
    public static final String COLUMN_FARMACO_GRUPO 	            = "grupofarmaco_id";
    public static final String COLUMN_FARMACIA_ID 	                = "farmacia_id";


    public static final int FARMACO_DISPONIVEL 	=1;
    public static final int FARMACO_INDISPONIVEL 	= 0;

    private static final long serialVersionUID = 1L;

    @DatabaseField
    private long id;
    @DatabaseField
    private String designacao;
    @DatabaseField
    private int disponibilidade;
    @DatabaseField()
    private double preco;
    @DatabaseField(columnName = COLUMN_FARMACIA_ID, foreign = true, foreignAutoRefresh = true)
    private Farmacia farmacia;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] logo;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] image;

    private ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty(GrupoFarmaco.TABLE_NAME_FARMACO)
    @DatabaseField(columnName = COLUMN_FARMACO_GRUPO, foreign = true, foreignAutoRefresh = true)
    private GrupoFarmaco grupoFarmaco;



    public Farmaco(){}

    public Farmaco(long id) {
        this.id = id;
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

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    @Bindable
    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    @Bindable
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    @Bindable
    public GrupoFarmaco getGrupoFarmaco() {
        return grupoFarmaco;
    }

    public void setGrupoFarmaco(GrupoFarmaco grupoFarmaco) {
        this.grupoFarmaco = grupoFarmaco;
        notifyPropertyChanged(BR.grupoFarmaco);
    }

    @Override
    public String getDescricao() {
        return this.designacao;
    }

    @Override
    public JSONObject toJsonObject() throws JsonProcessingException, JSONException {
        JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(this));
        return jsonObject;
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
    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    @Override
    public Farmaco fromJson(String jsonData) throws IOException {
        return objectMapper.readValue(jsonData, Farmaco.class);
    }

    @Override
    public Farmaco fromJsonObject(JSONObject response) throws IOException {
        return objectMapper.readValue(String.valueOf(response), Farmaco.class);
    }
}
