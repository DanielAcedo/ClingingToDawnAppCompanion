package com.danielacedo.clingingtodawn.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.danielacedo.clingingtodawn.R;
import com.danielacedo.clingingtodawn.interfaces.IRegisterMvp;
import com.danielacedo.clingingtodawn.preferences.AccountPreference;

import java.util.regex.Pattern;

/**
 * Created by Daniel on 10/11/16.
 */

public class RegisterPresenter implements IRegisterMvp.Presenter{

    IRegisterMvp.View view;

    public RegisterPresenter(IRegisterMvp.View view){
        this.view = view;
    }

    @Override
    public void validateCredentials(String user, String pass, String confirmPass, String email, String confirmEmail, String province, String city, boolean isCompany, String companyName) {
        if(TextUtils.isEmpty(user)) {
            view.setMessageError(((Context) view).getResources().getString(R.string.err_emptyData), R.id.edt_UserRegister);
        }
        else if(TextUtils.isEmpty(pass)){
            view.setMessageError(((Context) view).getResources().getString(R.string.err_emptyData), R.id.edt_PassRegister);
        }
        else if(!Pattern.matches(".*[0-9].*", pass)){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_Password_Digit), R.id.edt_PassRegister);
        }
        else if(!Pattern.matches(".*[a-z].*",pass) || !Pattern.matches(".*[A-Z].*",pass)){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_Password_UpperLowerCase), R.id.edt_PassRegister);
        }
        else if(pass.length()<8){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_Password_Length), R.id.edt_PassRegister);
        }
        else if(!pass.equals(confirmPass)){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_passwordMismatch), R.id.edt_ConfirmPass);
        }
        else if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_emailNotValid), R.id.edt_Email);
        }
        else if(!email.equals(confirmEmail)){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_emailMismatch), R.id.edt_ConfirmEmail);
        }
        else if(isCompany && companyName.equals("")){
            view.setMessageError(((Context)view).getResources().getString(R.string.err_companyNameEmpty), R.id.edt_CompanyNameRegister);
        }else{
            AccountPreference accountPreference = AccountPreference.getInstance((Context)view);
            accountPreference.putUser(user);
            accountPreference.putPassword(pass);

            Toast.makeText((Context)view, R.string.Toast_SuccessfulRegister, Toast.LENGTH_SHORT).show();
            ((Activity)view).finish();
        }
    }
}
