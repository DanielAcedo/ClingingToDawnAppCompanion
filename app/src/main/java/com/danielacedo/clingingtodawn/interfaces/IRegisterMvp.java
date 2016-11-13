package com.danielacedo.clingingtodawn.interfaces;

/**
 * Created by Daniel on 10/11/16.
 */

public interface IRegisterMvp {

    int OK = 0; // Success
    int PASSWORD_DIGIT = 1; //The password doesn't comply with the password minimum digit policy
    int PASSWORD_UPPERLOWERCASE = 2; //The password doesn't comply with the password minimum lowercase/uppercase policy
    int PASSWORD_LENGTH = 3; //The password doesn't comply with the password length policy
    int DATA_EMPTY = 4; //The user or password field is empty

    interface View{
        /**
         * Sends an order to the view to display a message error
         * @param messageError The message to be displayed
         * @author Daniel Acedo Calderón
         */
        void setMessageError(String messageError, int view);
    }

    interface Presenter{
        /**
         * Checks whether the submitted register information is valid and acts in consequence
         * @param user User login
         * @param pass Password login
         */
        void validateCredentials(String user, String pass, String confirmPass, String email, String confirmEmail,
                                 String province, String city, boolean isCompanyString, String companyName);

    }
}
