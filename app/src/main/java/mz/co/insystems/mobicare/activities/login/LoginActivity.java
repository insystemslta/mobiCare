package mz.co.insystems.mobicare.activities.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.login.registration.UserRegistrationActivity;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.databinding.ActivityLoginBinding;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.model.user.UserDao;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;
import mz.co.insystems.mobicare.util.Constants;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends BaseActivity {
    User user= new User();
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        activityLoginBinding.setUser(user);

     //   try {
         //   getmUserDao().create(createUser());
  //      } catch (SQLException e) {
    //        e.printStackTrace();
      //  }
        view = activityLoginBinding.getRoot();

      Button btnLogin= view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getUserName()!=null && user.getPassword()!=null){

                try {
                    User userDB= getmUserDao().authenticateUser(user);
                    if(userDB !=null){
                        Toast.makeText(LoginActivity.this, userDB.getUserName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), UserRegistrationActivity.class);
                             intent.putExtra("userLoged", userDB);
                        startActivity(intent);

                    }else
                    {
                        Toast.makeText(LoginActivity.this, " Dados Errados", Toast.LENGTH_SHORT).show();
                    }
                        } catch (SQLException e) {
                    e.printStackTrace();
                }
                }else{
                    Toast.makeText(LoginActivity.this, " Falha na Autenticacao. Contacte o Admin", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
private User createUser(){
        return new User("Bento", "bento18");
        }

    private void autenticatorService(){

        final MobicareSyncService service = new MobicareSyncService();
        Uri.Builder uri =  service.initServiceUri();
        uri.appendPath(MobicareSyncService.SERVICE_ENTITY_USER);
        uri.appendPath(MobicareSyncService.SERVICE_AUTHENTICATE);
        final String url = uri.build().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    service.makeJsonObjectRequest(Request.Method.POST, url, user.toJsonObject(), user, new VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            //syncProgress.dismiss();
                            showMessageDialog(getString(R.string.error_msg_sync_failed));
                        }

                        @Override
                        public void onResponse(JSONObject response, int myStatusCode) {
                            if (myStatusCode == Constants.HTTP_CREATED){
                             //   createUser(response);
                            }
                           // syncProgress.dismiss();

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
    }
