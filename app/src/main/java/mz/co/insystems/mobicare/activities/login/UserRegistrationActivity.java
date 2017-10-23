package mz.co.insystems.mobicare.activities.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.base.BaseActivity;

public class UserRegistrationActivity extends BaseActivity {

    public UserRegistrationActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }
}
