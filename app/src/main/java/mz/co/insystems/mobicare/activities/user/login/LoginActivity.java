package mz.co.insystems.mobicare.activities.user.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.farmacia.SearchActivity;
import mz.co.insystems.mobicare.activities.user.registration.UserRegistrationActivity;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.databinding.ActivityLoginBinding;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.SyncError;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;
import mz.co.insystems.mobicare.util.Utilities;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends BaseActivity implements LoginActions{

    private ActivityLoginBinding activityLoginBinding;
    private boolean keyboardListenersAttached = false;
    private ViewGroup rootLayout;
    private  boolean keybordOpen = false;
    private int monitorNumber =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        LoginManager loginManager = new LoginManager(LoginActivity.this);

        if (getCurrentUser() == null){
            setCurrentUser(new User());
        }

        activityLoginBinding.setUser(getCurrentUser());
        activityLoginBinding.setPresenter(loginManager);

        attachKeyboardListeners();
    }




    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            int viewHeight= activityLoginBinding.getRoot().getRootView().getHeight();

            int rootHight = activityLoginBinding.getRoot().getHeight();



            int heightDiff = activityLoginBinding.getRoot().getRootView().getHeight() - activityLoginBinding.getRoot().getHeight();


            LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(LoginActivity.this);

            if (monitorNumber == 0 && rootHight > monitorNumber){
                monitorNumber = rootHight;
                onShowKeyboard();
            }else if (monitorNumber > 0 && rootHight > monitorNumber){
                monitorNumber = rootHight;
                onHideKeyboard();
            }else if (monitorNumber > 0 && rootHight < monitorNumber){
                monitorNumber = rootHight;
                onShowKeyboard();
            }


        }
    };

    protected void onShowKeyboard() {
        this.keybordOpen = true;
        activityLoginBinding.setLoginActivity(this);
    }
    protected void onHideKeyboard() {
        this.keybordOpen = false;
        activityLoginBinding.setLoginActivity(this);
    }

    protected void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }

        rootLayout = (ViewGroup) activityLoginBinding.getRoot();
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
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
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        intent.putExtra(User.TABLE_NAME, getCurrentUser());
        startActivity(intent);
    }

    private void tryToUpdateLoginStatusOnWeb(User user, String url) {
        service.makeJsonObjectRequest(Request.Method.POST, url, null, user, new VolleyResponseListener() {

            @Override
            public void onError(SyncError error) {

            }

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

    @Override
    public int getMarginDimension() {
        return keybordOpen ? R.dimen.dimen_20dp : R.dimen.dimen_50dp;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (keyboardListenersAttached) {
            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(keyboardLayoutListener);
        }
    }

    public boolean isKeybordOpen() {
        return keybordOpen;
    }

    public void setKeybordOpen(boolean keybordOpen) {
        this.keybordOpen = keybordOpen;
    }
}
