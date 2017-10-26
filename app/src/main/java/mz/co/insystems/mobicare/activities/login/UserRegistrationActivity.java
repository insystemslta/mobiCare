package mz.co.insystems.mobicare.activities.login;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.Bundle;

import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.base.BaseActivity;
import mz.co.insystems.mobicare.model.entidade.user.User;
import mz.co.insystems.mobicare.model.entidade.user.UserDao;

public class UserRegistrationActivity extends BaseActivity {
    private UserDao mUserDao;
    private ProgressDialog syncProgress;
    private User user;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){

        }
    };

    public UserRegistrationActivity() {
        super();
        try {
            mUserDao = getHelper().getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }

    private void doUserSync(int method){
        syncProgress = ProgressDialog.show(UserRegistrationActivity.this, ""
                , getString(R.string.user_sync_in_progress));
        syncProgress.setCancelable(true);

        Intent userSyncService = new Intent(getApplicationContext(), UserRegistrationActivity.class);
        Messenger messenger = new Messenger(handler);
        userSyncService.putExtra("messenger", messenger);
        userSyncService.putExtra(User.class.getSimpleName().toLowerCase(), user);
        userSyncService.putExtra("method", method);


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "com.mamlambo.intent.action.MESSAGE_PROCESSED";


        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
