package mz.co.insystems.mobicare.model.entidade.farmaco;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.farmaco.grupofarmaco.GrupoFarmaco;

/**
 *
 */
public class Farmaco extends BaseVO {

    public static final String TABLE_NAME_FARMACO			        = "farmaco";
    public static final String COLUMN_FARMACO_ID 			    = "id";
    public static final String COLUMN_FARMACO_DESIGNACAO 			= "designacao";
    public static final String COLUMN_FARMACO_DISPONIBILIDADE = "disponibilidade";
    public static final String COLUMN_FARMACO_PRECO 	= "preco";
    public static final String COLUMN_FARMACO_GRUPO 	= "grupofarmaco_id";


    public static final int FARMACO_DISPONIVEL 	=1;
    public static final int FARMACO_INDISPONIVEL 	= 0;

    private static final long serialVersionUID = 1L;

    private long id;
    private String designacao;
    private int disponibilidade;
    private double preco;

    @DatabaseField(columnName = COLUMN_FARMACO_GRUPO, foreign = true, foreignAutoRefresh = true)
    private GrupoFarmaco grupoFarmaco;



    public Farmaco(){}

    public Farmaco(long id) {
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
    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    @Bindable
    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    @Bindable
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    @Bindable
    public GrupoFarmaco getGrupoFarmaco() {
        return grupoFarmaco;
    }

    public void setGrupoFarmaco(GrupoFarmaco grupoFarmaco) {
        this.grupoFarmaco = grupoFarmaco;
        notifyPropertyChanged(BR.grupoFarmaco);
    }

    @Override
    public BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException {
        return null;
    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        return null;
    }
}
