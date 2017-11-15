package mz.co.insystems.mobicare.model.entidade.endereco.distrito;

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
@DatabaseTable(tableName = Distrito.TABLE_NAME_DISTRITO, daoClass = DistritoDao.class)
public class Distrito extends BaseVO {

    public static final String TABLE_NAME_DISTRITO                       = "distrito";
    public static final String COLUMN_DISTRITO_ID 			             = "id";
    public static final String COLUMN_DISTRITO_DESIGNACAO 		         = "designacao";
    public static final String COLUMN_DISTRITO_DESCRICAO                 = "descricao";
    public static final String COLUMN_DISTRITO_PROVINCIA_ID              = "provincia_id";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_DISTRITO_ID, id = true, generatedId = false)
    private long id;
    @DatabaseField(columnName = COLUMN_DISTRITO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_DISTRITO_DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = COLUMN_DISTRITO_PROVINCIA_ID, foreign = true, foreignAutoRefresh = true)
    private Provincia provincia;


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

    public Distrito(long id) {
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
        Distrito distrito = new Distrito();
        distrito.setId(jsonObject.getInt(COLUMN_DISTRITO_ID));
        distrito.setDescricao(jsonObject.getString(COLUMN_DISTRITO_DESCRICAO));
        distrito.setDesignacao(jsonObject.getString(COLUMN_DISTRITO_DESIGNACAO));
        distrito.setProvincia((Provincia) new Provincia().convertVoFromJSON(jsonObject.getJSONObject(Provincia.TABLE_NAME_PROVINCIA)));
        return distrito;
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_DISTRITO_ID,           this.getId());
        jsonObject.put(COLUMN_DISTRITO_DESIGNACAO,   this.getDesignacao());
        jsonObject.put(COLUMN_DISTRITO_DESCRICAO,    this.getDescricao());
        jsonObject.put(Provincia.TABLE_NAME_PROVINCIA,    this.getProvincia().generateJsonObject());
        return jsonObject;
    }
}
