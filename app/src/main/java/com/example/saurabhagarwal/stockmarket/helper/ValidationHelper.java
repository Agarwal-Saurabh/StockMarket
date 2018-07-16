package com.example.saurabhagarwal.stockmarket.helper;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by lenovo1 on 7/27/2016.
 */
public class ValidationHelper {

    /*
    * validation Email*/
    public static boolean validateEmail(String email) {

        String regex="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (email.isEmpty())
        {
            return false;
        }
        else if(!email.matches(regex))
        {
            return false;
        }
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    /*
   * validation Name*/
    public static  boolean validateName(String name) {
        String regex = "^[a-zA-Z\\s]*$";
        if (name.isEmpty() || !name.matches(regex)) {
            return false;
        }
        return true;
    }
    /*
        * validation phone*/
    public static boolean validateTelephone(String number)
    {
        if(Patterns.PHONE.matcher(number).matches() && number.length() == 10)
        {
            return true;
        }
        else {
            return false;
        }
    }
    /*
       * validation phone*/
    public static boolean validatePassword(String password)
    {
        if(!password.equalsIgnoreCase("") && password.length() >= 6)
        {
            return true;
        }
        else {
            return false;
        }
    }

}
