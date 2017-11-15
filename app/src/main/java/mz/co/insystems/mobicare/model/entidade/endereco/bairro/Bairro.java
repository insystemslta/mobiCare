package mz.co.insystems.mobicare.model.entidade.endereco.bairro;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.entidade.endereco.provincia.Provincia;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Bairro.TABLE_NAME_BAIRRO, daoClass = BairroDao.class)
public class Bairro extends BaseVO {

    public static final String TABLE_NAME_BAIRRO                    = "bairro";
    public static final String COLUMN_BAIRRO_ID 			        = "id";
    public static final String COLUMN_BAIRRO_DESIGNACAO 			= "designacao";
    public static final String COLUMN_BAIRRO_DESCRICAO              = "descricao";
    public static final String COLUMN_BAIRRO_ZONA                   = "zona";
    public static final String COLUMN_BAIRRO_MUNICIPIO              = "municipio_id";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_BAIRRO_ID, id = true, generatedId = false)
    private long id;
    @DatabaseField(columnName = COLUMN_BAIRRO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_BAIRRO_DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = COLUMN_BAIRRO_MUNICIPIO, foreign = true, foreignAutoRefresh = true)
    private Municipio municipio;


    public Bairro(){}

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

    public Bairro(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException {
        Bairro bairro = new Bairro();
        bairro.setId(jsonObject.getInt(COLUMN_BAIRRO_ID));
        bairro.setDescricao(jsonObject.getString(COLUMN_BAIRRO_DESCRICAO));
        bairro.setDesignacao(jsonObject.getString(COLUMN_BAIRRO_DESIGNACAO));
        bairro.setMunicipio((Municipio) new Municipio().convertVoFromJSON(jsonObject.getJSONObject(Provincia.TABLE_NAME_PROVINCIA)));
        return bairro;
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
