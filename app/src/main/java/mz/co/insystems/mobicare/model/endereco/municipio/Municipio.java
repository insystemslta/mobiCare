package mz.co.insystems.mobicare.model.endereco.municipio;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.common.SimpleAdapter;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;

/**
 * Created by voloide on 9/15/16.
 */
@DatabaseTable(tableName = Municipio.TABLE_NAME_MUNICIPIO, daoClass = MunicipioDaoImpl.class)
public class Municipio extends BaseVO implements SimpleAdapter {

    public static final String TABLE_NAME_MUNICIPIO                    = "municipio";
    public static final String COLUMN_MUNICIPIO_ID 			        = "id";
    public static final String COLUMN_MUNICIPIO_DESIGNACAO 			= "designacao";
    public static final String COLUMN_MUNICIPIO_DESCRICAO              = "descricao";
    public static final String COLUMN_MUNICIPIO_PROVINCIA_ID              = "provincia_id";


    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = COLUMN_MUNICIPIO_ID, id = true, generatedId = false)
    private int id;
    @DatabaseField(columnName = COLUMN_MUNICIPIO_DESIGNACAO)
    private String designacao;
    @DatabaseField(columnName = COLUMN_MUNICIPIO_DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = COLUMN_MUNICIPIO_PROVINCIA_ID, foreign = true, foreignAutoRefresh = true)
    private Provincia provincia;


    public Municipio(){}


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

    public Municipio(int id) {
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

}
