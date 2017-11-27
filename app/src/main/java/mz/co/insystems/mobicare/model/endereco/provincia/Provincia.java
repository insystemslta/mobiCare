package mz.co.insystems.mobicare.model.endereco.provincia;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.common.SimpleAdapter;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Provincia.TABLE_NAME_PROVINCIA, daoClass = ProvinciaDaoImpl.class)
public class Provincia extends BaseVO implements SimpleAdapter{

    public static final String TABLE_NAME_PROVINCIA                 = "provincia";
    public static final String COLUMN_PROVINCIA_ID 			        = "id";
    public static final String COLUMN_PROVINCIA_DESIGNACAO 			= "designacao";
    public static final String COLUMN_PROVINCIA_DESCRICAO           = "descricao";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_PROVINCIA_ID, id = true, generatedId = false)
    private int id;
    @DatabaseField(columnName = COLUMN_PROVINCIA_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_PROVINCIA_DESCRICAO)
    private String descricao;


    public Provincia(){}

    public Provincia(int id) {
        this.id = id;
    }

    public Provincia(JSONObject jsonObject) throws JSONException {
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", designacao='" + designacao + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {

        this.setId(jsonObject.getInt(COLUMN_PROVINCIA_ID));
        this.setDescricao(jsonObject.getString(COLUMN_PROVINCIA_DESCRICAO));
        this.setDesignacao(jsonObject.getString(COLUMN_PROVINCIA_DESIGNACAO));
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COLUMN_PROVINCIA_ID,           this.getId());
        jsonObject.put(COLUMN_PROVINCIA_DESIGNACAO,   this.getDesignacao());
        jsonObject.put(COLUMN_PROVINCIA_DESCRICAO,    this.getDescricao());
        return jsonObject;
    }
}
