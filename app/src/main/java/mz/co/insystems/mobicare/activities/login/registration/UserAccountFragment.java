package mz.co.insystems.mobicare.activities.login.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.FragmentChangeListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class UserAccountFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_account, container, false);
        Button continuar = view.findViewById(R.id.btnContinue);


        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOtherFragment();
            }
        });
        return view;
    }

    private UserRegistrationActivity getMyActivity() {
        return (UserRegistrationActivity) getActivity();
    }

    public void showOtherFragment(){
        Fragment fr = new PersonalDataFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }


}
