package mz.co.insystems.mobicare.activities.login;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.model.entidade.Pessoa;
import mz.co.insystems.mobicare.model.entidade.contacto.Contacto;
import mz.co.insystems.mobicare.model.entidade.endereco.Endereco;
import mz.co.insystems.mobicare.model.entidade.user.User;
import mz.co.insystems.mobicare.model.entidade.user.UserDao;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;

public class UserRegistrationActivity extends BaseActivity {
    private UserDao mUserDao;
    private ProgressDialog syncProgress;


    public UserRegistrationActivity() {
        super();
        /*try {
            mUserDao = getHelper().getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        final Button sync 			= (Button) 	 findViewById(R.id.btnSync);

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUserSync();

            }
        });
    }

    public void onClickButton(View view) {
        if(view instanceof Button){
            if (((Button) view).getId() == R.id.btnSync) {
                this.doUserSync();
            }
        }
    }

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



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    service.makeJsonObjectRequest(Request.Method.PUT, url, user.genarateJsonObject(), user, new VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            syncProgress.dismiss();
                            Log.d("tag", message);
                        }

                        @Override
                        public void onResponse(JSONObject response, int myStatusCode) {
                            syncProgress.dismiss();
                            printUser(response);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void printUser(JSONObject response) {
        Log.d("tag",response.toString());
    }
}
