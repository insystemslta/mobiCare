package mz.co.insystems.mobicare.model.endereco.bairro;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.common.SimpleAdapter;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Bairro.TABLE_NAME_BAIRRO, daoClass = BairroDaoImpl.class)
public class Bairro extends BaseVO implements SimpleAdapter {

    public static final String TABLE_NAME_BAIRRO                    = "bairro";
    public static final String COLUMN_BAIRRO_ID 			        = "id";
    public static final String COLUMN_BAIRRO_DESIGNACAO 			= "designacao";
    public static final String COLUMN_BAIRRO_DESCRICAO              = "descricao";
    public static final String COLUMN_BAIRRO_ZONA                   = "zona";
    public static final String COLUMN_BAIRRO_MUNICIPIO              = "municipio_id";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_BAIRRO_ID, id = true, generatedId = false)
    private int id;
    @DatabaseField(columnName = COLUMN_BAIRRO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_BAIRRO_DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = COLUMN_BAIRRO_MUNICIPIO, foreign = true, foreignAutoRefresh = true)
    private Municipio municipio;


    public Bairro(){}

    public Bairro(JSONObject jsonObject) throws JSONException {
        this.convertVoFromJSON(jsonObject);
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

    public Bairro(int id) {
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
    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {

        this.setId(jsonObject.getInt(COLUMN_BAIRRO_ID));
        this.setDescricao(jsonObject.getString(COLUMN_BAIRRO_DESCRICAO));
        this.setDesignacao(jsonObject.getString(COLUMN_BAIRRO_DESIGNACAO));
        this.setMunicipio(new Municipio(jsonObject.getJSONObject(Municipio.TABLE_NAME_MUNICIPIO)));
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_BAIRRO_ID,           this.getId());
        jsonObject.put(COLUMN_BAIRRO_DESIGNACAO,   this.getDesignacao());
        jsonObject.put(COLUMN_BAIRRO_DESCRICAO,    this.getDescricao());
        jsonObject.put(Municipio.TABLE_NAME_MUNICIPIO,    this.getMunicipio().generateJsonObject());
        return jsonObject;
    }
}
