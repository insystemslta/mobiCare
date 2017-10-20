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

import mz.co.insystems.mobicare.R;


/**
 * Created by Voloide Tamele on 9/19/2017.
 */
public class BaseActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = null;
    protected String TAG;

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
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
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


    protected DatabaseHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return dbHelper;
    }

    protected void showMessageDialog(final String message) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
