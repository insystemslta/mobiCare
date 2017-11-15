package mz.co.insystems.mobicare.model.entidade.endereco;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.BR;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.entidade.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.entidade.endereco.postoadministrativo.PostoAdministrativo;

/**
 * Created by voloide on 9/15/16.
 */
public class Endereco extends BaseVO {

    public static final String TABLE_NAME_ENDERECO			        = "endereco";
    public static final String COLUMN_ENDERECO_ID 			        = "id";
    public static final String COLUMN_ENDERECO_LATITUDE 			= "latitude";
    public static final String COLUMN_ENDERECO_LONGITUDE            = "longitude";
    public static final String COLUMN_ENDERECO_BAIRRO_ID 	        = "bairro_id";
    public static final String COLUMN_ENDERECO_POSTO_ID 	        = "posto_id";
    public static final String COLUMN_ENDERECO_RUA_AV 	            = "ruaAvenida";
    public static final String COLUMN_ENDERECO_ZONA 	            = "zona";
    public static final String COLUMN_ENDERECO_NCASA 	            = "ncasa";

    private static final long serialVersionUID = 1L;

    private long id;
    private double latitude;
    private double longitude;
    private String zona;

    @DatabaseField(columnName = COLUMN_ENDERECO_BAIRRO_ID, foreign = true, foreignAutoRefresh = true)
    private Bairro bairro;

    @DatabaseField(columnName = COLUMN_ENDERECO_POSTO_ID, foreign = true, foreignAutoRefresh = true)
    private PostoAdministrativo postoAdministrativo;

    private String ruaAvenida;


    private String ncasa;


    public Endereco(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Endereco(){}

    public Endereco(long id) {
        this.id = id;
    }

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    @Bindable
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    @Bindable
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
    @Bindable
    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    @Bindable
    public PostoAdministrativo getPostoAdministrativo() {
        return postoAdministrativo;
    }

    public void setPostoAdministrativo(PostoAdministrativo postoAdministrativo) {
        this.postoAdministrativo = postoAdministrativo;
    }
    @Bindable
    public String getRuaAvenida() {
        return ruaAvenida;
    }

    public void setRuaAvenida(String ruaAvenida) {
        this.ruaAvenida = ruaAvenida;
    }
    @Bindable
    public String getNcasa() {
        return ncasa;
    }

    public void setNcasa(String ncasa) {
        this.ncasa = ncasa;
    }

    @Override
    public BaseVO convertVoFromJSON(JSONObject jsonObject) throws JSONException {
        return null;
    }

    @Override
    public JSONObject genarateJsonObject() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(COLUMN_ENDERECO_ID, this.getId());
        object.put(COLUMN_ENDERECO_LATITUDE, this.getLatitude());
        object.put(COLUMN_ENDERECO_LONGITUDE, this.getLongitude());
        object.put(COLUMN_ENDERECO_BAIRRO_ID, this.getBairro());
        object.put(COLUMN_ENDERECO_POSTO_ID, this.getPostoAdministrativo());
        object.put(COLUMN_ENDERECO_RUA_AV, this.getRuaAvenida());
        object.put(COLUMN_ENDERECO_ZONA, this.getZona());
        object.put(COLUMN_ENDERECO_NCASA, this.getZona());
        return object;
    }
}
