package mz.co.insystems.mobicare.model.endereco.postoadministrativo;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.common.SimpleAdapter;
import mz.co.insystems.mobicare.model.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = PostoAdministrativo.TABLE_NAME_POSTO, daoClass = PostoAdministrativoDaoImpl.class)
public class PostoAdministrativo extends BaseVO implements SimpleAdapter {

    public static final String TABLE_NAME_POSTO                         = "postoadministrativo";
    public static final String COLUMN_POSTO_ID 			                = "id";
    public static final String COLUMN_POSTO_DESIGNACAO 			        = "designacao";
    public static final String COLUMN_POSTO_DESCRICAO                   = "descricao";
    public static final String COLUMN_POSTO_DISTRITO_ID                 = "distrito_id";

    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_POSTO_ID, id = true, generatedId = false)
    private int id;
    @DatabaseField(columnName = COLUMN_POSTO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_POSTO_DESCRICAO)
    private String descricao;
    @DatabaseField(columnName = COLUMN_POSTO_DISTRITO_ID, foreign = true, foreignAutoRefresh = true)
    private Distrito distrito;


    public PostoAdministrativo(){}

    public PostoAdministrativo(int id) {
        this.id = id;
    }

    public PostoAdministrativo(JSONObject jsonObject) throws JSONException {
        this.convertVoFromJSON(jsonObject);
    }


    public int getId() {
        return id;
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

    public void setId(int id) {

        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    @Override
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {

        this.setId(jsonObject.getInt(COLUMN_POSTO_ID));
        this.setDescricao(jsonObject.getString(COLUMN_POSTO_DESCRICAO));
        this.setDesignacao(jsonObject.getString(COLUMN_POSTO_DESIGNACAO));
        this.setDistrito(new Distrito(jsonObject.getJSONObject(Distrito.TABLE_NAME_DISTRITO)));
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_POSTO_ID,           this.getId());
        jsonObject.put(COLUMN_POSTO_DESIGNACAO,   this.getDesignacao());
        jsonObject.put(COLUMN_POSTO_DESCRICAO,    this.getDescricao());
        jsonObject.put(Municipio.TABLE_NAME_MUNICIPIO,    this.getDistrito().generateJsonObject());
        return jsonObject;
    }
}