package mz.co.insystems.mobicare.model.endereco.bairro;

import android.databinding.Bindable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.common.SimpleAdapter;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;

/**
 * Created by voloide on 9/15/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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

}
