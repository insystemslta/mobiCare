package mz.co.insystems.mobicare.activities.user.registration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.user.registration.fragment.UserAccountFragment;
import mz.co.insystems.mobicare.base.BaseActivity;

public class UserRegistrationActivity extends BaseActivity implements UserRegistrationView{

    private ProgressDialog syncProgress;
    private FragmentManager manager;
    private UserRegistrationPresenterImpl presenter;



    public UserRegistrationActivity() {
        super();
        this.manager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        addSimpleToolbar();

        presenter = new UserRegistrationPresenterImpl(UserRegistrationActivity.this, getmUserDao());

        if (savedInstanceState == null){
            UserAccountFragment accountFragment = new UserAccountFragment();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.root_container, accountFragment);
            transaction.commit();
        }
    }

    @Override
    public void showLoading() {
        syncProgress = ProgressDialog.show(UserRegistrationActivity.this, ""
                , getString(R.string.localizacao_settings_sync_in_progress));
        syncProgress.setCancelable(true);
    }

    @Override
    public void hideLoading() {
        syncProgress.dismiss();
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
        hideLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public UserRegistrationPresenterImpl getPresenter() {
        return presenter;
    }

    public void setPresenter(UserRegistrationPresenterImpl presenter) {
        this.presenter = presenter;
    }

}
