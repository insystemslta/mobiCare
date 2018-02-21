package mz.co.insystems.mobicare.activities.login.registration;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;

import java.io.IOException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.FragmentChangeListener;
import mz.co.insystems.mobicare.databinding.FragmentUserAccountBinding;
import mz.co.insystems.mobicare.model.contacto.Contacto;
import mz.co.insystems.mobicare.model.user.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class UserAccountFragment extends Fragment {
    User user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentUserAccountBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_account, container, false);
        View view = binding.getRoot();



        user = getMyActivity().getCurrentUser();

        binding.setUser(user);

        Button continuar = view.findViewById(R.id.btnContinue);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contacto = new Contacto();
                contacto.setId(1000);
                contacto.setEmail("hdjdj@jsdkjsd");
                contacto.setAuxMobileNumber("62627828");

                try {
                    contacto.toJson();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                try {
                    contacto.toJsonObject();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //System.out.println(contacto.toJson());
                Contacto contacto1 = new Contacto();
                try {
                    contacto1 = contacto1.fromJsonObject(contacto.toJsonObject());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        return view;
    }

    public void continueRegistrtion(){
        getMyActivity().setCurrentUser(user);
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
