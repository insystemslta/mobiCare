package mz.co.insystems.mobicare.model.entidade.endereco.bairro;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.entidade.endereco.postoadministrativo.PostoAdministrativo;

/**
 * Created by voloide on 9/15/16.
 */
public class Bairro extends BaseVO {

    public static final String TABLE_NAME_BAIRRO                    = "bairro";
    public static final String COLUMN_BAIRRO_ID 			        = "id";
    public static final String COLUMN_BAIRRO_DESIGNACAO 			= "designacao";
    public static final String COLUMN_BAIRRO_DESCRICAO              = "descricao";
    public static final String COLUMN_BAIRRO_ZONA                   = "zona";
    public static final String COLUMN_BAIRRO_MUNICIPIO              = "municipio_id";
    public static final String COLUMN_BAIRRO_POSTOADMINISTRATIVO    = "postoadministrativo_id";


    private static final long serialVersionUID = 1L;

    private long id;
    private String designacao;
    private String descricao;
    @DatabaseField(columnName = COLUMN_BAIRRO_POSTOADMINISTRATIVO, foreign = true, foreignAutoRefresh = true)
    private PostoAdministrativo postoAdministrativo;

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
    public PostoAdministrativo getPostoAdministrativo() {
        return postoAdministrativo;
    }

    public void setPostoAdministrativo(PostoAdministrativo postoAdministrativo) {
        this.postoAdministrativo = postoAdministrativo;
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
        return null;
    }

    @Override
    public JSONObject genarateJsonObject() throws JSONException {
        return null;
    }
}
