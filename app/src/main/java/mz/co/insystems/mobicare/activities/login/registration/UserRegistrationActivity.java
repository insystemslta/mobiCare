package mz.co.insystems.mobicare.activities.login.registration;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.FragmentChangeListener;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.model.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.endereco.postoadministrativo.PostoAdministrativo;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;

public class UserRegistrationActivity extends BaseActivity implements FragmentChangeListener, Runnable{

    private ProgressDialog syncProgress;
    private FragmentManager manager;
    private List<String> errorList;

    public UserRegistrationActivity() {
        super();
        this.manager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        addSimpleToolbar();

        try {
            setUpAppSettings();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (savedInstanceState == null){
            UserAccountFragment accountFragment = new UserAccountFragment();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.root_container, accountFragment);
            transaction.commit();
        }
    }

    private void setUpAppSettings() throws InterruptedException {
        initProgress();

        try {
            if (!getProvinciaDao().queryForAll().isEmpty())
                try {
                    doLocalizacoesSync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doLocalizacoesSync() throws InterruptedException {
        //syncProvincias();

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncProvincias();
            }
        }).start();
    }

    private void initProgress() {
        syncProgress = ProgressDialog.show(UserRegistrationActivity.this, ""
                , getString(R.string.localizacao_settings_sync_in_progress));
        syncProgress.setCancelable(true);
    }

    private void syncBairros() {
        service.makeJsonArrayRequest(Request.Method.GET, buildGetAllUrlString(Bairro.TABLE_NAME_BAIRRO), null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                onSyncError(Bairro.TABLE_NAME_BAIRRO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Bairro bairro = new Bairro();
                        bairro = bairro.fromJsonObject(response.getJSONObject(i));
                        getBairroDao().createIfNotExists(bairro);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                syncProgress.dismiss();
            }
        });
    }

    private void syncPostos() {
        service.makeJsonArrayRequest(Request.Method.GET, buildGetAllUrlString(PostoAdministrativo.TABLE_NAME_POSTO), null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                onSyncError(PostoAdministrativo.TABLE_NAME_POSTO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        PostoAdministrativo postoAdministrativo = new PostoAdministrativo();
                        postoAdministrativo = postoAdministrativo.fromJsonObject(response.getJSONObject(i));
                        getPostoDao().createIfNotExists(postoAdministrativo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (noSyncError()){
                    syncMunicipios();
                }else terminateSyncWithError();
            }
        });
    }

    private void syncMunicipios() {
        service.makeJsonArrayRequest(Request.Method.GET, buildGetAllUrlString(Municipio.TABLE_NAME_MUNICIPIO), null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                onSyncError(Municipio.TABLE_NAME_MUNICIPIO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Municipio municipio = new Municipio();
                        municipio = municipio.fromJsonObject(response.getJSONObject(i));
                        getMunicipioDao().createIfNotExists(municipio);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (noSyncError()){
                    syncBairros();
                }else terminateSyncWithError();
            }
        });
    }

    private void syncDistritos() {
        service.makeJsonArrayRequest(Request.Method.GET, buildGetAllUrlString(Distrito.TABLE_NAME_DISTRITO), null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                onSyncError(Distrito.TABLE_NAME_DISTRITO, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Distrito distrito = new Distrito();
                        distrito = distrito.fromJsonObject(response.getJSONObject(i));
                        getDistritoDao().createIfNotExists(distrito);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (noSyncError()) {
                    syncPostos();
                }else terminateSyncWithError();
            }
        });
    }

    private void syncProvincias(){
        service.makeJsonArrayRequest(Request.Method.GET, buildGetAllUrlString(Provincia.TABLE_NAME_PROVINCIA), null, getCurrentUser(), new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                onSyncError(Provincia.TABLE_NAME_PROVINCIA, message);
            }

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {

                for(int i=0;i<response.length();i++){
                    try {
                        Provincia provincia = new Provincia();
                        provincia = provincia.fromJsonObject(response.getJSONObject(i));
                        getProvinciaDao().createIfNotExists(provincia);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (noSyncError()) {
                    syncDistritos();
                }else {
                    terminateSyncWithError();
                }

            }
        });
    }

    private void terminateSyncWithError() {
        syncProgress.dismiss();
    }

    private boolean noSyncError() {
        return errorList == null || errorList.isEmpty();
    }

    private void onSyncError(String job, String error){
        if (errorList == null) errorList = new ArrayList<>();
        errorList.add(job+" "+error);

        syncProgress.dismiss();
        //showMessageDialog(error);
    }

    private String buildGetAllUrlString(String entinty) {
        Uri.Builder uri =  service.initServiceUri();
        uri.appendPath(entinty);
        uri.appendPath(MobicareSyncService.SERVICE_GET_ALL);
        return uri.build().toString();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        syncProgress.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void run() {
        try {
            doLocalizacoesSync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
