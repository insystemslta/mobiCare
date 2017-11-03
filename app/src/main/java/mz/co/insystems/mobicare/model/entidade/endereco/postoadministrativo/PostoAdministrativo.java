package mz.co.insystems.mobicare.model.entidade.endereco.postoadministrativo;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.entidade.endereco.provincia.Provincia;

/**
 * Created by voloide on 9/15/16.
 */
public class PostoAdministrativo extends BaseVO {

    public static final String TABLE_NAME_POSTO                         = "postoadministrativo";
    public static final String COLUMN_POSTO_ID 			                = "id";
    public static final String COLUMN_POSTO_DESIGNACAO 			        = "designacao";
    public static final String COLUMN_POSTO_DESCRICAO                   = "descricao";
    public static final String COLUMN_POSTO_DISTRITO_ID                 = "distrito_id";
    public static final String COLUMN_POSTO_PROVINCIA_ID                = "provincia_id";

    private static final long serialVersionUID = 1L;

    private long id;
    private String designacao;
    private String descricao;
    @DatabaseField(columnName = COLUMN_POSTO_PROVINCIA_ID, foreign = true, foreignAutoRefresh = true)
    private Provincia provincia;

    @DatabaseField(columnName = COLUMN_POSTO_DISTRITO_ID, foreign = true, foreignAutoRefresh = true)
    private Distrito distrito;


    public PostoAdministrativo(){}

    public PostoAdministrativo(long id) {
        this.id = id;
    }


    public long getId() {
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
    @Bindable
    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
}
