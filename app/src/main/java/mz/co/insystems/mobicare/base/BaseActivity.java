package mz.co.insystems.mobicare.base;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.model.endereco.bairro.BairroDao;
import mz.co.insystems.mobicare.model.endereco.distrito.DistritoDao;
import mz.co.insystems.mobicare.model.endereco.municipio.MunicipioDao;
import mz.co.insystems.mobicare.model.endereco.postoadministrativo.PostoAdministrativoDao;
import mz.co.insystems.mobicare.model.endereco.provincia.ProvinciaDao;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.model.user.UserDao;


/**
 * Created by Voloide Tamele on 9/19/2017.
 */
public class BaseActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = null;
    protected String TAG;
    private User currentUser;

    private UserDao mUserDao;
    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;
    private MunicipioDao municipioDao;
    private PostoAdministrativoDao postoDao;
    private BairroDao bairroDao;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public BaseActivity(){

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected Toolbar addHomeButtonToolbar(){
        return initToolbar(true, new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    protected Toolbar addSimpleToolbar(){
        return initToolbar(false, new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    private Toolbar initToolbar(boolean addHomeButton, ColorDrawable icon) {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        toolbar.bringToFront();


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(addHomeButton);
        actionbar.setHomeButtonEnabled(addHomeButton);
        actionbar.setIcon(icon);
        return toolbar;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;

        }
    }


    protected DatabaseHelper getHelper(Context context) {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
        }
        return dbHelper;
    }

    protected void showMessageDialog(final String message) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public UserDao getmUserDao() {
        try {
            mUserDao = getHelper(getApplicationContext()).getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mUserDao;
    }

    public ProvinciaDao getProvinciaDao() {
        try {
            provinciaDao = getHelper(getApplicationContext()).getProvinciaDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provinciaDao;
    }

    public DistritoDao getDistritoDao() {
        try {
            distritoDao = getHelper(getApplicationContext()).getDistritoDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return distritoDao;
    }

    public MunicipioDao getMunicipioDao() {
        try {
            municipioDao = getHelper(getApplicationContext()).getMunicipioDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return municipioDao;
    }

    public PostoAdministrativoDao getPostoDao() {
        try {
            postoDao = getHelper(getApplicationContext()).getPostoAdministrativoDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postoDao;
    }

    public BairroDao getBairroDao() {
        try {
            bairroDao = getHelper(getApplicationContext()).getBairroDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bairroDao;
    }
}
