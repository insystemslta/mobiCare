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

public class UserRegistrationActivity extends BaseActivity implements FragmentChangeListener{

    private ProgressDialog syncProgress;
    private FragmentManager manager;

    public UserRegistrationActivity() {
        super();
        this.manager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        addSimpleToolbar();

        setUpAppSettings();


        if (savedInstanceState == null){
            UserAccountFragment accountFragment = new UserAccountFragment();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.root_container, accountFragment);
            transaction.commit();
        }
    }

    private void setUpAppSettings() {
        try {
            if (getProvinciaDao().queryForAll().isEmpty()) doLocalizacoesSync();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doLocalizacoesSync() {
        initProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncProvincias();
                syncDistritos();
                syncMunicipios();
                syncPostos();
                syncBairros();
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
                        getBairroDao().create(bairro);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                afterSyncFinish();
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
                        getPostoDao().create(postoAdministrativo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                afterSyncFinish();
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
                        getMunicipioDao().create(municipio);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                afterSyncFinish();
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
                        getDistritoDao().create(distrito);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                afterSyncFinish();
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
                        getProvinciaDao().create(provincia);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                afterSyncFinish();
            }
        });
    }

    private void afterSyncFinish() {
        syncProgress.dismiss();
    }

    private void onSyncError(String job, String error){
        syncProgress.dismiss();
        showMessageDialog(getString(R.string.error_msg_sync_failed));
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
}
