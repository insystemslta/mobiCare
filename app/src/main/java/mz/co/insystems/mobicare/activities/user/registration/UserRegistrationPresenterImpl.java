package mz.co.insystems.mobicare.activities.user.registration;

import java.sql.SQLException;

import mz.co.insystems.mobicare.activities.user.registration.fragment.PersonalDataFragment;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.model.user.UserDao;

/**
 * Created by Voloide Tamele on 3/16/2018.
 */

public class UserRegistrationPresenterImpl implements UserRegistrationPresenter {

    UserRegistrationView userRegistrationView;
    UserDao userDao;

    public UserRegistrationPresenterImpl(UserRegistrationView userRegistrationView, UserDao userDao) {
        this.userRegistrationView = userRegistrationView;
        this.userDao = userDao;
    }

    @Override
    public void doSave(User user) throws SQLException {
        userRegistrationView.showLoading();
        userDao.create(user);
        userRegistrationView.hideLoading();
    }

    @Override
    public void continueRegistration(User user) {
        ((UserRegistrationActivity) userRegistrationView).setCurrentUser(user);
        userRegistrationView.replaceFragment(new PersonalDataFragment());
    }
}
