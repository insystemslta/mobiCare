package mz.co.insystems.mobicare.model.entidade.farmacia.servicos;

import android.databinding.Bindable;

import mz.co.insystems.mobicare.base.BaseVO;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
public class Servico extends BaseVO {
    public static final String TABLE_NAME_SERVICO			                        = "servico";
    public static final String COLUMN_SERVICO_ID 			                        = "id";
    public static final String COLUMN_SERVICO_DESIGNACAO			                = "designacao";
    public static final String COLUMN_SERVICO_DESCRICAO			                    = "descricao";
    public static final String COLUMN_SERVICO_ESTADO			                    = "estado";



    private long id;
    private String designacao;
    private String descricao;
    private int estado;

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
}
