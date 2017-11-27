package mz.co.insystems.mobicare.model.farmacia.servicos;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.farmacia.Farmacia;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
@DatabaseTable(tableName = Servico.TABLE_NAME_SERVICO, daoClass = ServicoDaoImpl.class)
public class Servico extends BaseVO {
    public static final String TABLE_NAME_SERVICO			                        = "servico";
    public static final String COLUMN_SERVICO_ID 			                        = "id";
    public static final String COLUMN_SERVICO_DESIGNACAO			                = "designacao";
    public static final String COLUMN_SERVICO_DESCRICAO			                    = "descricao";
    public static final String COLUMN_SERVICO_ESTADO			                    = "estado";



    @DatabaseField(columnName = COLUMN_SERVICO_ID, id = true, generatedId = false)
    private long id;
    @DatabaseField(columnName = COLUMN_SERVICO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_SERVICO_DESCRICAO)
    private String descricao;
    @DatabaseField
    private int estado;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Farmacia farmacia;

    public Servico() {
    }

    public long getId() {
        return id;
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

    @Override
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        return null;
    }
}
