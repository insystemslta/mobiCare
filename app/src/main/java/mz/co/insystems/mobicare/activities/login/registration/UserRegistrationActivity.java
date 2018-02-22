package mz.co.insystems.mobicare.activities.login.registration;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.volley.Request;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.FragmentChangeListener;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.model.contacto.Contacto;
import mz.co.insystems.mobicare.model.endereco.Endereco;
import mz.co.insystems.mobicare.model.pessoa.Pessoa;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;
import mz.co.insystems.mobicare.util.Constants;

public class UserRegistrationActivity extends BaseActivity implements FragmentChangeListener{



    private ProgressDialog syncProgress;
    private FragmentManager manager = getSupportFragmentManager();

    public UserRegistrationActivity() {
        setCurrentUser(new User());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        addSimpleToolbar();

        doUserSync();

        if (savedInstanceState == null){
            UserAccountFragment accountFragment = new UserAccountFragment();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.root_container, accountFragment);
            transaction.commit();
        }






/*


        final Button continuar 			= findViewById(R.id.btnContinue);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonalDataFragment personalDataFragment = new PersonalDataFragment();

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.root_container, personalDataFragment, "personal_data");
                transaction.commit();
            }
        });*/


    }

    /*public void onClickButton(View view) {
        if(view instanceof Button){
            if (view.getId() == R.id.btnSync) {
                this.doUserSync();
            }
        }
    }
    */

    private void doUserSync(){

        syncProgress = ProgressDialog.show(UserRegistrationActivity.this, ""
                , getString(R.string.user_sync_in_progress));
        syncProgress.setCancelable(true);

        final MobicareSyncService service = new MobicareSyncService();
        Uri.Builder uri =  service.initServiceUri();
        uri.appendPath(MobicareSyncService.SERVICE_ENTITY_USER);
        uri.appendPath(MobicareSyncService.SERVICE_CREATE);
        final String url = uri.build().toString();

        final User user = new User();
        user.setUserName("844441662");
        user.setPassword("1000");
        user.setPessoa(new Pessoa());
        user.getPessoa().setName("Jaime");
        user.getPessoa().setSurname("Horrr");
        user.getPessoa().setContacto(new Contacto(100, "voloidet@gmail.com", "637278292", "73872992"));
        user.getPessoa().setEndereco(new Endereco(34.778893,78.9887));

        user.setEstado(1);

        final Contacto contacto = new Contacto(100, "voloidet@gmail.com", "637278292", "73872992");
        /*try {
            List<User> userList = getmUserDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    service.makeJsonObjectRequest(Request.Method.PUT, url, user.toJsonObject(), user, new VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            syncProgress.dismiss();
                            showMessageDialog(getString(R.string.error_msg_sync_failed));
                        }

                        @Override
                        public void onResponse(JSONObject response, int myStatusCode) {
                            if (myStatusCode == Constants.HTTP_CREATED){
                                createUser(response);
                            }
                            syncProgress.dismiss();

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void createUser(JSONObject response) {
        User user = new User();
        try {
            getmUserDao().create(user.fromJsonObject(response));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
