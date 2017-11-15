package mz.co.insystems.mobicare.model.entidade.endereco.municipio;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.endereco.provincia.Provincia;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Municipio.TABLE_NAME_MUNICIPIO, daoClass = MunicipioDao.class)
public class Municipio extends BaseVO {

    public static final String TABLE_NAME_MUNICIPIO                    = "municipio";
    public static final String COLUMN_MUNICIPIO_ID 			        = "id";
    public static final String COLUMN_MUNICIPIO_DESIGNACAO 			= "designacao";
    public static final String COLUMN_MUNICIPIO_DESCRICAO              = "descricao";
    public static final String COLUMN_MUNICIPIO_PROVINCIA_ID              = "provincia_id";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_MUNICIPIO_ID, id = true, generatedId = false)
    private long id;
    @DatabaseField(columnName = COLUMN_MUNICIPIO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_MUNICIPIO_DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = COLUMN_MUNICIPIO_PROVINCIA_ID, foreign = true, foreignAutoRefresh = true)
    private Provincia provincia;


    public Municipio(){}

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

    public Municipio(long id) {
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
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException {
        Municipio municipio = new Municipio();
        municipio.setId(jsonObject.getInt(COLUMN_MUNICIPIO_ID));
        municipio.setDescricao(jsonObject.getString(COLUMN_MUNICIPIO_DESCRICAO));
        municipio.setDesignacao(jsonObject.getString(COLUMN_MUNICIPIO_DESIGNACAO));
        municipio.setProvincia((Provincia) new Provincia().convertVoFromJSON(jsonObject.getJSONObject(Provincia.TABLE_NAME_PROVINCIA)));
        return municipio;
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_MUNICIPIO_ID,           this.getId());
        jsonObject.put(COLUMN_MUNICIPIO_DESIGNACAO,   this.getDesignacao());
        jsonObject.put(COLUMN_MUNICIPIO_DESCRICAO,    this.getDescricao());
        jsonObject.put(Provincia.TABLE_NAME_PROVINCIA,    this.getProvincia().generateJsonObject());
        return jsonObject;
    }
}
