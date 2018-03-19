package mz.co.insystems.mobicare.activities.user.registration;

import mz.co.insystems.mobicare.common.FragmentChangeListener;

/**
 * Created by Voloide Tamele on 3/16/2018.
 */

public interface UserRegistrationView extends FragmentChangeListener {

    void showLoading();
    void hideLoading();
}
