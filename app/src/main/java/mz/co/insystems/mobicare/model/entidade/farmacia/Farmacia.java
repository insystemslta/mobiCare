package mz.co.insystems.mobicare.model.entidade.farmacia;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.List;

import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.contacto.Contact;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;
import mz.co.insystems.mobicare.model.entidade.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.entidade.farmacia.servicos.Servico;

/**
 * Created by Voloide Tamele on 10/23/2017.
 */
public class Farmacia extends BaseVO {
    public static final String TABLE_NAME_FARMACIA			                = "farmacia";
    public static final String COLUMN_FARMACIA_ID 			                = "id";
    public static final String COLUMN_FARMACIA_NOME			                = "nome";
    public static final String COLUMN_FARMACIA_ENDERECO 			        = "endereco_id";
    public static final String COLUMN_FARMACIA_CONTACTO 			        = "contacto_id";
    public static final String COLUMN_FARMACIA_ESTADO 			            = "estado";


    private static final long serialVersionUID = 1L;

    private long id;
    private String nome;
    private int estado;
    @DatabaseField(columnName = COLUMN_FARMACIA_ENDERECO, foreign = true, foreignAutoRefresh = true)
    private Endereco endereco;

    @DatabaseField(columnName = COLUMN_FARMACIA_CONTACTO, foreign = true, foreignAutoRefresh = true)
    private Contact contact;
    @ForeignCollectionField
    private List<Servico> servicos;
    public Farmacia(long id, String nome, int estado, Endereco endereco, Contact contact) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.endereco = endereco;
        this.contact = contact;
    }
    public Farmacia() {

    }
    @Override
    public String toString() {
        return "GrupoFarmaco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado=" + estado +
                ", endereco=" + endereco +
                ", contact=" + contact +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Bindable
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Bindable
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    @Bindable
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    @Bindable
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
}
