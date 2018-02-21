package mz.co.insystems.mobicare.model.farmaco.grupofarmaco;

import mz.co.insystems.mobicare.base.BaseVO;


/**
 * Created by Voloide Tamele on 10/23/2017.
 */
public class GrupoFarmaco extends BaseVO {
    public static final String TABLE_NAME_FARMACIA			                = "grupoDefarmaco";
    public static final String COLUMN_FARMACIA_ID 			                = "id";
    public static final String COLUMN_FARMACIA_NOME			                = "designacao";


    private static final long serialVersionUID = 1L;

    private long id;
    private String designacao;

    public GrupoFarmaco() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }


}
