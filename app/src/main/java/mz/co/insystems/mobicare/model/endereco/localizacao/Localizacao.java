package mz.co.insystems.mobicare.model.endereco.localizacao;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.base.BaseVO;
import mz.co.insystems.mobicare.model.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.endereco.postoadministrativo.PostoAdministrativo;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;

/**
 * Created by Voloide Tamele on 11/27/2017.
 */

public class Localizacao extends BaseVO implements LocalizacaoSync {

    private Provincia selectedProvincia;
    private Distrito selectedDistrito;
    private Municipio selectedMunicipio;

    private boolean isRural;

    private MobicareSyncService service;
    private List<Provincia> provinciaList;
    private List<Distrito> distritoList;
    private List<Municipio> municipioList;
    private List<Bairro> bairroList;
    private List<PostoAdministrativo> postoList;

    private BaseActivity currentActivity;
    private List<String> syncErrorList;

    public Localizacao(BaseActivity currentActivity, final boolean isRural) {
        this.currentActivity = currentActivity;
        this.isRural = isRural;
        this.syncErrorList = new ArrayList<>();

        try {
            this.provinciaList = currentActivity.getProvinciaDao().queryForAll();
            if (provinciaList == null || provinciaList.size() <= 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        syncProvincias();
                        if (isRural){
                            if (noSyncError()) syncDistritos();
                            if (noSyncError()) syncPostos();
                        }else {
                            if (noSyncError()) syncMunicipios();
                            if (noSyncError()) syncBairros();
                        }
                        if (!noSyncError()) showSyncError();
                    }
                }).start();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSyncError() {
        // TO-DO
    }

    private boolean noSyncError() {
        return syncErrorList == null || syncErrorList.isEmpty();
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
    public void syncProvincias() {
        service.makeJsonArrayRequest(Request.Method.GET, currentActivity.buildGetAllUrlString(Provincia.TABLE_NAME_PROVINCIA), null, currentActivity.getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                //onSyncError(Provincia.TABLE_NAME_PROVINCIA, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Provincia provincia = new Provincia();
                        provincia = provincia.fromJsonObject(response.getJSONObject(i));
                        currentActivity.getProvinciaDao().createIfNotExists(provincia);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void syncDistritos() {
        service.makeJsonArrayRequest(Request.Method.GET, currentActivity.buildGetAllUrlString(Distrito.TABLE_NAME_DISTRITO), null, currentActivity.getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                //onSyncError(Distrito.TABLE_NAME_DISTRITO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Distrito distrito = new Distrito();
                        distrito = distrito.fromJsonObject(response.getJSONObject(i));
                        currentActivity.getDistritoDao().createIfNotExists(distrito);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void syncPostos() {
        service.makeJsonArrayRequest(Request.Method.GET, currentActivity.buildGetAllUrlString(PostoAdministrativo.TABLE_NAME_POSTO), null, currentActivity.getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                //onSyncError(PostoAdministrativo.TABLE_NAME_POSTO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        PostoAdministrativo postoAdministrativo = new PostoAdministrativo();
                        postoAdministrativo = postoAdministrativo.fromJsonObject(response.getJSONObject(i));
                        currentActivity.getPostoDao().createIfNotExists(postoAdministrativo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void syncMunicipios() {
        service.makeJsonArrayRequest(Request.Method.GET, currentActivity.buildGetAllUrlString(Municipio.TABLE_NAME_MUNICIPIO), null, currentActivity.getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                //onSyncError(Municipio.TABLE_NAME_MUNICIPIO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Municipio municipio = new Municipio();
                        municipio = municipio.fromJsonObject(response.getJSONObject(i));
                        currentActivity.getMunicipioDao().createIfNotExists(municipio);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void syncBairros() {
        service.makeJsonArrayRequest(Request.Method.GET, currentActivity.buildGetAllUrlString(Distrito.TABLE_NAME_DISTRITO), null, currentActivity.getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                //onSyncError(Distrito.TABLE_NAME_DISTRITO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Distrito distrito = new Distrito();
                        distrito = distrito.fromJsonObject(response.getJSONObject(i));
                        currentActivity.getDistritoDao().createIfNotExists(distrito);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
