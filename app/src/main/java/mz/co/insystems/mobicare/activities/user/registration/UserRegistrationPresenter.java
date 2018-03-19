package mz.co.insystems.mobicare.activities.user.registration;

import java.sql.SQLException;

import mz.co.insystems.mobicare.model.user.User;

/**
 * Created by Voloide Tamele on 3/16/2018.
 */

public interface UserRegistrationPresenter {

    void doSave(User user) throws SQLException;
    void continueRegistration(User user);
}
