package mz.co.insystems.mobicare.activities.user.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.user.registration.UserRegistrationActivity;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.databinding.ActivityLoginBinding;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;
import mz.co.insystems.mobicare.util.Utilities;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends BaseActivity implements LoginActions{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        LoginManager loginManager = new LoginManager(LoginActivity.this);

        if (getCurrentUser() == null){
            setCurrentUser(new User());
        }

        activityLoginBinding.setUser(getCurrentUser());
        activityLoginBinding.setPresenter(loginManager);
    }

    @Override
    public void doLogin(final User user) {
        try {
            if (getmUserDao().isAuthentic(user)){
                setCurrentUser(getmUserDao().getByCredencials(user));

                if (Utilities.isNetworkAvailable(LoginActivity.this)) {
                    Uri.Builder uri = service.initServiceUri();
                    uri.appendPath(MobicareSyncService.SERVICE_ENTITY_USER);
                    uri.appendPath(MobicareSyncService.SERVICE_AUTHENTICATE);
                    final String url = uri.build().toString();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            tryToUpdateLoginStatusOnWeb(user, url);
                        }
                    }).start();
                }
                redirectToSearch();
            }else Utilities.displayCommonAlertDialog(LoginActivity.this, getString(R.string.user_password_invalid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void redirectToSearch() {
        Intent intent = new Intent(getApplicationContext(), UserRegistrationActivity.class);
        startActivity(intent);
    }

    private void tryToUpdateLoginStatusOnWeb(User user, String url) {
        service.makeJsonObjectRequest(Request.Method.POST, url, null, user, new VolleyResponseListener() {
            @Override
            public void onError(String message) {}

            @Override
            public void onResponse(JSONObject response, int myStatusCode) {}

            @Override
            public void onResponse(JSONArray response, int myStatusCode) {}
        });
    }

    @Override
    public void initNewUserCreation() {
        Intent intent = new Intent(getApplicationContext(), UserRegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    public void initPasswordReset() {

    }
}
