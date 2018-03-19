package mz.co.insystems.mobicare.activities.user.registration.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.List;

import mz.co.insystems.mobicare.R;
import mz.co.insystems.mobicare.activities.user.registration.UserRegistrationActivity;
import mz.co.insystems.mobicare.common.SimpleSpinnerAdapter;
import mz.co.insystems.mobicare.model.endereco.bairro.Bairro;
import mz.co.insystems.mobicare.model.endereco.distrito.Distrito;
import mz.co.insystems.mobicare.model.endereco.localizacao.Localizacao;
import mz.co.insystems.mobicare.model.endereco.municipio.Municipio;
import mz.co.insystems.mobicare.model.endereco.postoadministrativo.PostoAdministrativo;
import mz.co.insystems.mobicare.model.endereco.provincia.Provincia;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class PersonalDataFragment extends Fragment {
    private Spinner provSpinner;
    private Spinner distSpinner;
    private Spinner munSpinner;
    private Spinner postoSpinner;
    private Spinner bairroSpinner;
    private RadioGroup radioGroup;

    private Localizacao localizacao;

    public PersonalDataFragment() {
    }

    private boolean isRural(){
        return radioGroup.getCheckedRadioButtonId() == R.id.rdRural;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        PersonalDataFragmentDataBinding binding = DataBindingUtil.inflate(inflater,
//                R.layout.fragment_personal_data, container, false);

        View view = inflater.inflate(R.layout.fragment_personal_data, container, false);

        provSpinner = view.findViewById(R.id.provincia);
        distSpinner = view.findViewById(R.id.distrito);
        munSpinner = view.findViewById(R.id.municipio);
        postoSpinner = view.findViewById(R.id.posto);
        bairroSpinner = view.findViewById(R.id.bairro);

        radioGroup = view.findViewById(R.id.rdgZona);

        localizacao = new Localizacao(getMyActivity(), isRural());
        loadProvinciaSpinner(localizacao.getProvinciaList());

        loadSpinnerSchematics();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                reloadLocalizacao();
                loadSpinnerSchematics();
            }
        });

        bairroSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getMyActivity().getCurrentUser().getPessoa().getEndereco().setBairro((Bairro) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        postoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getMyActivity().getCurrentUser().getPessoa().getEndereco().setPostoAdministrativo((PostoAdministrativo) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        provSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    localizacao.setSelectedProvincia((Provincia) parent.getItemAtPosition(position));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (isRural()){
                    loadDistritosSpinner(localizacao.getDistritoList());
                }else {
                    loadMunicipalSpinner(localizacao.getMunicipioList());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        distSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    localizacao.setSelectedDistrito((Distrito) parent.getItemAtPosition(position));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                loadPostoSpinner(localizacao.getPostoList());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        munSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    localizacao.setSelectedMunicipio((Municipio) parent.getItemAtPosition(position));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                loadBairroSpinner(localizacao.getBairroList());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        // Inflate the layout for this fragment
        return view;
    }

    private void loadSpinnerSchematics() {
        if (isRural()){
            munSpinner.setVisibility(View.GONE);
            bairroSpinner.setVisibility(View.GONE);
            distSpinner.setVisibility(View.VISIBLE);
            postoSpinner.setVisibility(View.VISIBLE);
        }else {
            munSpinner.setVisibility(View.VISIBLE);
            bairroSpinner.setVisibility(View.VISIBLE);
            distSpinner.setVisibility(View.GONE);
            postoSpinner.setVisibility(View.GONE);
        }
    }

    private void reloadLocalizacao() {
        localizacao = new Localizacao(getMyActivity(), isRural());
    }

    private void loadBairroSpinner(List<Bairro> bairroList) {
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, bairroList);
        bairroSpinner.setAdapter(adapter);
    }

    private void loadPostoSpinner(List<PostoAdministrativo> postoList) {
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, postoList);
        postoSpinner.setAdapter(adapter);
    }

    private void loadDistritosSpinner(List<Distrito> distritoList) {
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, distritoList);
        distSpinner.setAdapter(adapter);
    }

    private void loadMunicipalSpinner(List<Municipio> municipioList) {
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, municipioList);
        munSpinner.setAdapter(adapter);
    }

    private void loadProvinciaSpinner(List<Provincia> provinciaList) {
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(getMyActivity(), R.layout.simple_spinner_item, provinciaList);
        provSpinner.setAdapter(adapter);
    }

    private UserRegistrationActivity getMyActivity() {
        return (UserRegistrationActivity) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
