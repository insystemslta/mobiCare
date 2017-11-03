package mz.co.insystems.mobicare.model.entidade.endereco.provincia;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import mz.co.insystems.mobicare.base.BaseVO;

import mz.co.insystems.mobicare.BR;

/**
 * Created by voloide on 9/15/16.
 */
public class Provincia extends BaseVO {

    public static final String TABLE_NAME_PROVINCIA                 = "provincia";
    public static final String COLUMN_PROVINCIA_ID 			        = "id";
    public static final String COLUMN_PROVINCIA_DESIGNACAO 			= "designacao";
    public static final String COLUMN_PROVINCIA_DESCRICAO           = "descricao";


    private static final long serialVersionUID = 1L;

    private long id;

    private String designacao;
    private String descricao;


    public Provincia(){}

    public Provincia(long id) {
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    

}