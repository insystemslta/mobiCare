package mz.co.insystems.mobicare.model.endereco.localizacao;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.endereco.postoadministrativo.PostoAdministrativo;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;

/**
 * Created by Voloide Tamele on 11/27/2017.
 */

public class Localizacao extends BaseVO {

    private Provincia selectedProvincia;
    private Distrito selectedDistrito;
    private Municipio selectedMunicipio;

    private boolean isRural;

    private List<Provincia> provinciaList;
    private List<Distrito> distritoList;
    private List<Municipio> municipioList;
    private List<Bairro> bairroList;
    private List<PostoAdministrativo> postoList;

    private BaseActivity currentActivity;

    public Localizacao(BaseActivity currentActivity, boolean isRural) {
        this.currentActivity = currentActivity;
        this.isRural = isRural;
        try {
            this.provinciaList = currentActivity.getProvinciaDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Provincia getSelectedProvincia() {
        return selectedProvincia;
    }

    public void setSelectedProvincia(Provincia selectedProvincia) throws SQLException {
        this.selectedProvincia = selectedProvincia;
        if (isRural){
            distritoList = currentActivity.getDistritoDao().queryBuilder().where().eq(Distrito.COLUMN_DISTRITO_PROVINCIA_ID, selectedProvincia.getId()).query();
        }else {
            municipioList = currentActivity.getMunicipioDao().queryBuilder().where().eq(Municipio.COLUMN_MUNICIPIO_PROVINCIA_ID, selectedProvincia.getId()).query();
        }

        resetBairroData();
        resetPostoData();

    }

    private void resetPostoData() {
        postoList = null;
    }

    private void resetBairroData() {
        bairroList = null;
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public boolean isRural() {
        return isRural;
    }

    public void setRural(boolean rural) {
        isRural = rural;
    }

    public Distrito getSelectedDistrito() {
        return selectedDistrito;
    }

    public void setSelectedDistrito(Distrito selectedDistrito) throws SQLException {
        postoList = currentActivity.getPostoDao().queryBuilder().where().eq(PostoAdministrativo.COLUMN_POSTO_DISTRITO_ID, selectedDistrito.getId()).query();
        this.selectedDistrito = selectedDistrito;
    }

    public Municipio getSelectedMunicipio() {
        return selectedMunicipio;
    }

    public void setSelectedMunicipio(Municipio selectedMunicipio) throws SQLException {
        bairroList = currentActivity.getBairroDao().queryBuilder().where().eq(Bairro.COLUMN_BAIRRO_MUNICIPIO, selectedMunicipio.getId()).query();
        this.selectedMunicipio = selectedMunicipio;
    }

    public List<Distrito> getDistritoList() {
        return distritoList;
    }

    public void setDistritoList(List<Distrito> distritoList) {
        this.distritoList = distritoList;
    }

    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    public List<Bairro> getBairroList() {
        return bairroList;
    }

    public void setBairroList(List<Bairro> bairroList) {
        this.bairroList = bairroList;
    }

    public List<PostoAdministrativo> getPostoList() {
        return postoList;
    }

    public void setPostoList(List<PostoAdministrativo> postoList) {
        this.postoList = postoList;
    }

    @Override
    public void convertVoFromJSON(JSONObject jsonObject) throws JSONException {

    }

    @Override
    public JSONObject generateJsonObject() throws JSONException {
        return null;
    }
}
