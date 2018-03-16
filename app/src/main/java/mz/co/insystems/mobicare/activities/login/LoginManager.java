package mz.co.insystems.mobicare.activities.login;

import mz.co.insystems.mobicare.model.user.User;

/**
 * Created by Voloide Tamele on 3/16/2018.
 */

public class LoginManager {
    private LoginActivity loginActions;

    public LoginManager(LoginActivity loginActions) {
        this.loginActions = loginActions;
    }

    public void login(User user){
        loginActions.navigateBack(user);
    }
}
