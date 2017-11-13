package mz.co.insystems.mobicare.activities.login;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;

import org.json.JSONObject;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.base.BaseActivity;
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

                setCurrentUser(new User());
                getCurrentUser().setUserName("844441662");
                getCurrentUser().setPassword("1000");
                doUserSync();

            }
        });
    }

    public void onClickButton(View view) {
        if(view instanceof Button){
            if (((Button) view).getId() == R.id.btnSync) {
                setCurrentUser(new User());
                getCurrentUser().setUserName("844441662");
                getCurrentUser().setPassword("1000");
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


        new Thread(new Runnable() {
            @Override
            public void run() {
               service.makeJsonObjectRequest(Request.Method.PUT, url, getCurrentUser().genarateJsonObject(), getCurrentUser(), new VolleyResponseListener() {
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
            }
        }).start();
    }

    private void printUser(JSONObject response) {
        Log.d("tag",response.toString());
    }
}
