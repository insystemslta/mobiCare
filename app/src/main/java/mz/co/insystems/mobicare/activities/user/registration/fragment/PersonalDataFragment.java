package mz.co.insystems.mobicare.activities.user.registration.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.user.login.LoginActivity;
import mz.co.insystems.mobicare.activities.user.registration.UserRegistrationActivity;
import mz.co.insystems.mobicare.activities.user.registration.fragment.presenter.PersonalDataFragmentEventHandlerImpl;
import mz.co.insystems.mobicare.activities.user.registration.fragment.view.PersonalDataFragmentView;
import mz.co.insystems.mobicare.common.LocalizacaoSpinnerAdapter;
import mz.co.insystems.mobicare.databinding.PersonalDataFragmentDataBinding;
import mz.co.insystems.mobicare.model.contacto.Contacto;
import mz.co.insystems.mobicare.model.endereco.Endereco;
import mz.co.insystems.mobicare.model.endereco.localizacao.Localizacao;
import mz.co.insystems.mobicare.model.pessoa.Pessoa;
import mz.co.insystems.mobicare.model.user.User;
import mz.co.insystems.mobicare.sync.MobicareSyncService;
import mz.co.insystems.mobicare.sync.SyncError;
import mz.co.insystems.mobicare.sync.VolleyResponseListener;
import mz.co.insystems.mobicare.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class PersonalDataFragment extends Fragment implements PersonalDataFragmentView {

    private PersonalDataFragmentDataBinding binding;
    private Localizacao localizacao;

    public PersonalDataFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_data, container, false);

        initMunicipalLocalizacao();

        PersonalDataFragmentEventHandlerImpl presenter = new PersonalDataFragmentEventHandlerImpl(PersonalDataFragment.this, getMyActivity().getmUserDao());
        initUser();

        binding.setUser(getMyActivity().getCurrentUser());
        binding.setPresenter(presenter);
        binding.setLocalizacao(localizacao);
        return binding.getRoot();
    }

    private void initUser() {
        getCurrentUser().setPessoa(new Pessoa());
        getCurrentUser().getPessoa().setContacto(new Contacto());
        getCurrentUser().getPessoa().setEndereco(new Endereco());
    }

    @Override
    public void loadLevelOneAdapters(Localizacao localizacao) {
        LocalizacaoSpinnerAdapter provinciaAdapter = new LocalizacaoSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, localizacao.getProvinciaList());
        binding.setProvinciaAdapter(provinciaAdapter);
    }

    @Override
    public void loadLevelTwoAdapters(Localizacao localizacao) {
        LocalizacaoSpinnerAdapter distritoAdapter = new LocalizacaoSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, localizacao.getDistritoList());
        LocalizacaoSpinnerAdapter municipioAdapter = new LocalizacaoSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, localizacao.getMunicipioList());
        binding.setDistritoAdapter(distritoAdapter);
        binding.setMunicipioAdapter(municipioAdapter);
    }

    @Override
    public void loadLevelThreeAdapters(Localizacao localizacao) {
        LocalizacaoSpinnerAdapter postoAdapter = new LocalizacaoSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, localizacao.getPostoList());
        LocalizacaoSpinnerAdapter bairroAdapter = new LocalizacaoSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, localizacao.getBairroList());
        binding.setPostoAdapter(postoAdapter);
        binding.setBairroAdapter(bairroAdapter);
    }

    @Override
    public void reloadAllAdapters(Localizacao localizacao) {
        loadLevelOneAdapters(localizacao);
        loadLevelTwoAdapters(localizacao);
        loadLevelThreeAdapters(localizacao);
    }

    @Override
    public void initMunicipalLocalizacao() {
        if (this.localizacao == null){
            this.localizacao = new Localizacao(getMyActivity(), PersonalDataFragment.this, false);
        }else {
            this.localizacao.setRural(false);
            this.localizacao.resetLevelTwoData();
            this.localizacao.resetLevelThreeData();
            reloadAllAdapters(this.localizacao);
        }
    }

    @Override
    public void initRuralLocalizacao() {
        if (this.localizacao == null){
            this.localizacao = new Localizacao(getMyActivity(), PersonalDataFragment.this, true);
        }else {
            this.localizacao.setRural(true);
            this.localizacao.resetLevelTwoData();
            this.localizacao.resetLevelThreeData();
            reloadAllAdapters(this.localizacao);
        }
    }

    private UserRegistrationActivity getMyActivity() {
        return (UserRegistrationActivity) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public User getCurrentUser() {
        return getMyActivity().getCurrentUser();
    }

    @Override
    public void doSave(User user) {
        showLoading();
        Uri.Builder uri =  getMyActivity().getService().initServiceUri();
        uri.appendPath(User.TABLE_NAME);
        uri.appendPath(MobicareSyncService.SERVICE_CREATE);
        final String url = uri.build().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getMyActivity().getService().makeJsonObjectRequest(Request.Method.PUT, url, getCurrentUser().toJsonObject(), getCurrentUser(), new VolleyResponseListener() {


                        @Override
                        public void onError(SyncError error) {
                            hideLoading();
                        }

                        @Override
                        public void onResponse(JSONObject response, int myStatusCode) {
                            if (myStatusCode == Constants.HTTP_CREATED){

                                Uri.Builder uri =  getMyActivity().getService().initServiceUri();
                                uri.appendPath(User.TABLE_NAME);
                                uri.appendPath(MobicareSyncService.URL_SERVICE_USER_GET_BY_CREDENTIALS);
                                final String url = uri.build().toString();

                                getMyActivity().getService().makeJsonObjectRequest(Request.Method.POST, url, null, getCurrentUser(), new VolleyResponseListener() {
                                    @Override
                                    public void onError(SyncError error) {
                                        hideLoading();
                                    }

                                    @Override
                                    public void onResponse(JSONObject response, int myStatusCode) {
                                        User user = new User();
                                        try {
                                            user = user.fromJsonObject(response);
                                            getMyActivity().getmUserDao().createIfNotExists(user);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        hideLoading();
                                        backToLogin(user);
                                    }

                                    @Override
                                    public void onResponse(JSONArray response, int myStatusCode) {}
                                });

                            }

                        }

                        @Override
                        public void onResponse(JSONArray response, int myStatusCode) {}
                    });
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            getMyActivity().getmUserDao().createIfNotExists(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void backToLogin(User user) {
        getMyActivity().setCurrentUser(user);
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTipoLocalizacaoClick(String tipoLocalizacao) {
        if (tipoLocalizacao.equalsIgnoreCase(getString(R.string.rural))){
            getLocalizacao().setRural(true);
        }else getLocalizacao().setRural(false);
    }

    @Override
    public void showLoading() {
        getMyActivity().showLoading(getContext(), null, getString(R.string.localizacao_settings_sync_in_progress));
    }

    @Override
    public void hideLoading() {
        getMyActivity().hideLoading();
    }
}
