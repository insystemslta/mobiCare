package mz.co.insystems.mobicare.model.endereco.distrito;

import android.databinding.Bindable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.base.json.JsonParseble;
import mz.co.insystems.mobicare.common.LocalizacaoObject;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Distrito.TABLE_NAME_DISTRITO, daoClass = DistritoDaoImpl.class)
public class Distrito extends BaseVO implements LocalizacaoObject, JsonParseble<Distrito> {

    public static final String TABLE_NAME_DISTRITO                       = "distrito";
    public static final String COLUMN_DISTRITO_ID 			             = "id";
    public static final String COLUMN_DISTRITO_DESIGNACAO 		         = "designacao";
    public static final String COLUMN_DISTRITO_DESCRICAO                 = "descricao";
    public static final String COLUMN_DISTRITO_PROVINCIA_ID              = "provincia_id";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_DISTRITO_ID, id = true, generatedId = false)
    private int id;
    @DatabaseField(columnName = COLUMN_DISTRITO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_DISTRITO_DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = COLUMN_DISTRITO_PROVINCIA_ID, foreign = true, foreignAutoRefresh = true)
    private Provincia provincia;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Distrito(){}


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

    public Distrito(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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
    public Distrito fromJson(String jsonData) throws IOException {
        return objectMapper.readValue(jsonData, Distrito.class);
    }

    @Override
    public Distrito fromJsonObject(JSONObject response) throws IOException {
        return objectMapper.readValue(String.valueOf(response), Distrito.class);
    }
}
