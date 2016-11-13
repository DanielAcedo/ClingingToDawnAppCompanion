package com.danielacedo.clingingtodawn;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.danielacedo.clingingtodawn.interfaces.IRegisterMvp;
import com.danielacedo.clingingtodawn.presenter.RegisterPresenter;

/**
 * Activity used for registering new users
 * @author Daniel Acedo Calderón
 */
public class Register_Activity extends AppCompatActivity  implements IRegisterMvp.View{

    IRegisterMvp.Presenter presenter;

    private Spinner spProvince;
    private Spinner spCity;
    private RadioGroup rg_typeClient;
    private TextView txv_CompanyNameRegister;
    private EditText edt_CompanyNameRegister;

    private EditText edt_UserRegister, edt_PassRegister, edt_ConfirmPass, edt_Email, edt_ConfirmEmail;

    private AdapterView.OnItemSelectedListener spinnerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        presenter = new RegisterPresenter(this);

        edt_UserRegister = (EditText)findViewById(R.id.edt_UserRegister);
        edt_PassRegister = (EditText)findViewById(R.id.edt_PassRegister);
        edt_ConfirmPass = (EditText)findViewById(R.id.edt_ConfirmPass);
        edt_Email = (EditText)findViewById(R.id.edt_Email);
        edt_ConfirmEmail = (EditText)findViewById(R.id.edt_ConfirmEmail);

        spProvince = (Spinner)findViewById(R.id.spn_ProvinceRegister);
        spCity = (Spinner)findViewById(R.id.spn_CityRegister);

        rg_typeClient = (RadioGroup)findViewById(R.id.rg_CompanyParticular);
        rg_typeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_Company:
                        showCompany(true);
                        break;
                    case R.id.rb_Particular:
                        showCompany(false);
                        break;
                }
            }
        });

        txv_CompanyNameRegister = (TextView)findViewById(R.id.txv_CompanyNameRegister);
        edt_CompanyNameRegister = (EditText)findViewById(R.id.edt_CompanyNameRegister);

        loadSpinnerProvince();

    }

    /**
     * Hides or shows the company name entry
     * @param value Whether the entry will be shown or not
     */
    private void showCompany(boolean value){
        txv_CompanyNameRegister.setVisibility(value ? View.VISIBLE : View.GONE);
        edt_CompanyNameRegister.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    /**
     * Shows the currently selected City
     */
    private void showCitySelected(){
        String city = spCity.getSelectedItem().toString();
        String province = spProvince.getSelectedItem().toString();

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.Toast_SelectedCity, city, province), Toast.LENGTH_SHORT).show();
    }

    /**
     * Loads the city spinner
     * @param position Position clicked on the province spinner
     */
    private void loadSpinnerCity(int position){
        ArrayAdapter<CharSequence> adapter;

        TypedArray resourceProvinces = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);

        int cityArrayId = resourceProvinces.getResourceId(position, 0);
        Log.d("Register", String.valueOf(cityArrayId));

        if(cityArrayId > 0) {
            adapter = ArrayAdapter.createFromResource(Register_Activity.this,
                    cityArrayId,
                    android.R.layout.simple_spinner_item
            );

            spCity.setAdapter(adapter);
        }
    }

    /**
     * Loads the province spinner
     */
    private void loadSpinnerProvince(){
        //Initialize province Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_spinner_item);
        spProvince.setAdapter(adapter);

        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (parent.getId()){

                    case R.id.spn_ProvinceRegister: //Load cities once a province is selected
                        loadSpinnerCity(position);
                        break;

                    case R.id.spn_CityRegister:
                        showCitySelected();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spProvince.setOnItemSelectedListener(spinnerListener);
        spCity.setOnItemSelectedListener(spinnerListener);
    }

    /**
     * OnClick method for the signUp button
     * @param view View that has been clicked
     */
    public void signUp(View view){

        String user = edt_UserRegister.getText().toString();
        String pass = edt_PassRegister.getText().toString();
        String confirmPass = edt_ConfirmPass.getText().toString();
        String email = edt_Email.getText().toString();
        String confirmEmail = edt_ConfirmEmail.getText().toString();
        String province = spProvince.getSelectedItem().toString();
        String city = spCity.getSelectedItem().toString();
        boolean isCompany = rg_typeClient.getCheckedRadioButtonId() == R.id.rb_Company;
        String companyName =  isCompany ? edt_CompanyNameRegister.getText().toString() : "";

        presenter.validateCredentials(user, pass, confirmPass, email, confirmEmail, province, city, isCompany, companyName);
    }

    @Override
    public void setMessageError(String messageError, int view) {
        switch(view){
            case R.id.edt_UserRegister:
                displayAndScrollErrorOnEditText(messageError, edt_UserRegister);
                break;
            case R.id.edt_PassRegister:
                displayAndScrollErrorOnEditText(messageError, edt_PassRegister);
                break;
            case R.id.edt_ConfirmPass:
                displayAndScrollErrorOnEditText(messageError, edt_ConfirmPass);
                break;
            case R.id.edt_Email:
                displayAndScrollErrorOnEditText(messageError, edt_Email);
                break;
            case R.id.edt_ConfirmEmail:
                displayAndScrollErrorOnEditText(messageError, edt_ConfirmEmail);
                break;
            case R.id.edt_CompanyNameRegister:
                displayAndScrollErrorOnEditText(messageError, edt_CompanyNameRegister);
                break;

        }
    }

    /**
     * Scrolls the parent ScrollView of an EditText and then correctly sets and shows the error message
     * @param messageError The message to be displayed
     * @param view View that displays the error message
     */
    private void displayAndScrollErrorOnEditText(String messageError, EditText view){
        view.setError(messageError);    //Sets message error
        view.getParent().requestChildFocus(view, view); //Scrolls the scrollview to the view
        if(view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);   //Focus on the view to make the message error visible
        }
    }
}
