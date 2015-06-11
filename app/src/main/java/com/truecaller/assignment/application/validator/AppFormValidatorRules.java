

package com.truecaller.assignment.application.validator;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
 public class AppFormValidatorRules {

    private static AppFormValidatorRules singleTon;

    private ValidationHolder validationHolder;

    static AppFormValidatorRules getInstance() {
           return singleTon;
    }
    public static void initialise(ValidationHolder validationHolder) {

            synchronized (AppFormValidatorRules.class) {
                singleTon = new AppFormValidatorRules(validationHolder);
        }
    }

    private AppFormValidatorRules(ValidationHolder validationHolder){
        this.validationHolder = validationHolder;
    }

    public boolean isEmailIdValid(EditText editText) {

        if(!validationHolder.getEmailValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getEmailValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        String email = editText.getText().toString();
        String EMAIL_PATTERN = validationHolder.getEmailValidator().getRegExpression();
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        boolean isValid = true;
        isValid = matcher.matches();
            if (!isValid) {
                editText.setError(validationHolder.getEmailValidator().getRegExMessage());
                editText.setSelectAllOnFocus(true);
                editText.requestFocus();
            }
        return isValid;
    }

    public boolean isNotBlank(EditText editText,String errorMessage) {
        boolean isValid = true;
        isValid = editText.getText().toString().trim().length() > 0;
        if (!isValid) {
            if(errorMessage == null){
                editText.setError("This field can not be empty !");

            }else{
                editText.setError(errorMessage);
            }
        }
        return isValid;
    }

    public boolean isNameValid(EditText editText){
        boolean isValid = true;
        isValid = (editText.getText().toString().trim().matches("[a-zA-Z ]+") != false);
        if(!isValid){
            editText.setError("Number is not allowed");
        }
        return isValid;
    }

}
