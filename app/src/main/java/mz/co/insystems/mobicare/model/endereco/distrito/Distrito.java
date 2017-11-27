package mz.co.insystems.mobicare.model.endereco.distrito;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.common.SimpleAdapter;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Distrito.TABLE_NAME_DISTRITO, daoClass = DistritoDaoImpl.class)
public class Distrito extends BaseVO implements SimpleAdapter {

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


    public Distrito(){}

    public Distrito(JSONObject jsonObject) throws JSONException {
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
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {

        this.setId(jsonObject.getInt(COLUMN_DISTRITO_ID));
        this.setDescricao(jsonObject.getString(COLUMN_DISTRITO_DESCRICAO));
        this.setDesignacao(jsonObject.getString(COLUMN_DISTRITO_DESIGNACAO));
        this.setProvincia(new Provincia(jsonObject.getJSONObject(Provincia.TABLE_NAME_PROVINCIA)));
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
