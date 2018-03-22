package mz.co.insystems.mobicare.activities.user.registration.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.user.registration.UserRegistrationActivity;
import mz.co.insystems.mobicare.activities.user.registration.fragment.presenter.UserAccountFragmentEventHandlerImpl;
import mz.co.insystems.mobicare.activities.user.registration.fragment.view.UserAccountFragmentView;
import mz.co.insystems.mobicare.common.FragmentChangeListener;
import mz.co.insystems.mobicare.databinding.FragmentUserAccountBinding;
import mz.co.insystems.mobicare.model.user.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class UserAccountFragment extends Fragment implements UserAccountFragmentView{
    User user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentUserAccountBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_account, container, false);
        View view = binding.getRoot();

        UserAccountFragmentEventHandlerImpl userAccountFragmentEventHandler = new UserAccountFragmentEventHandlerImpl(UserAccountFragment.this, getMyActivity().getmUserDao());

        user = getMyActivity().getCurrentUser();
        if (user == null) user = new User();

        binding.setUser(user);
        binding.setPresenter(userAccountFragmentEventHandler);

        return view;
    }

    private UserRegistrationActivity getMyActivity() {
        return (UserRegistrationActivity) getActivity();
    }

    @Override
    public void nextFragment(User user){
        getMyActivity().setCurrentUser(user);
        Fragment fr = new PersonalDataFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }


    public void cancelOperation() {

    }
}
