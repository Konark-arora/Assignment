/*
 * Copyright (c) 2015. This is under copyright of Applect Learning System pvt. ltd.
 * All rights are reserved.
 */

package com.meritnation.mnframework.application.validator;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hukum Singh on 8/12/14.
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


    public boolean isRegistrationPasswordValid(EditText editText) {
        boolean isValid = true;

        if(!validationHolder.getRegisterPasswordValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getRegisterPasswordValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        isValid = editText.getText().length() >= validationHolder.getRegisterPasswordValidator().getMinLength();
        if (!isValid) {
            editText.setError(validationHolder.getRegisterPasswordValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
        }
        return isValid;
    }

    public boolean isConfirmPasswordValid(EditText editText) {
        boolean isValid = true;

        if(!validationHolder.getConfirmPasswordValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getConfirmPasswordValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        isValid = editText.getText().length() >= validationHolder.getConfirmPasswordValidator().getMinLength();
        if (!isValid) {
            editText.setError(validationHolder.getConfirmPasswordValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
        }
        return isValid;
    }

    public boolean isConfirmPasswordEqualToPassword(EditText editPasswordText, EditText editConfirmText) {

        if(!editPasswordText.getText().toString().equals(editConfirmText.getText().toString())){

            editConfirmText.setError(validationHolder.getConfirmPasswordNotMatchValidator().getRegExMessage());
            editConfirmText.setSelectAllOnFocus(true);
            editConfirmText.requestFocus();
            return false;
        }else{
            return true;
        }


    }

    public boolean isLoginUserNameValid(EditText editText){
        if(!validationHolder.getLoginUserNameValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getLoginUserNameValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        boolean isValid = true;
        isValid = editText.getText().length() < validationHolder.getLoginUserNameValidator().getMaxLength();
        if(!isValid){
            editText.setError(validationHolder.getLoginUserNameValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
        }
        return isValid;
    }

    public boolean isLoginPasswordValid(EditText editText){
        if(!validationHolder.getLoginPasswordValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getLoginPasswordValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        boolean isValid = true;
        isValid = editText.getText().length() < validationHolder.getLoginPasswordValidator().getMaxLength();
        if(!isValid){
            editText.setError(validationHolder.getLoginPasswordValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
        }
        return isValid;
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
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
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

    public boolean isValidBoardSelected(Context context, Spinner spinner) {
        boolean isValid = true;
        isValid = spinner.getSelectedItemPosition() > 0;
        if (!isValid) {
            Toast.makeText(context, "Select board", Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    public boolean isValidGradeSelected(Context context, Spinner spinner) {
        boolean isValid = true;
        isValid = spinner.getSelectedItemPosition() > 0;
        if (!isValid) {
            Toast.makeText(context, "Select class", Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    public boolean isMobileNumberValid(EditText editText){
        boolean isValid = true;
        if(!validationHolder.getMobileValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getMobileValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        String mobile  = editText.getText().toString();
        String repeatedRegex =  "(.)\\1{7,}.*";
        if(mobile.matches(repeatedRegex)){
            editText.setError(validationHolder.getMobileValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
            return false;
        }
        
        isValid = (editText.getText().length() >= validationHolder.getMobileValidator().getMinLength() &&
                editText.getText().length() <= validationHolder.getMobileValidator().getMaxLength());
        if(!isValid){
            editText.setError(validationHolder.getMobileValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
            return false;
        }
        int len = 7;
        String numbers = "0123456789";
        String descending   = "9876543210";
        int start   = len - 1;
        String seq = "_" + mobile.substring(0,start);
        for (int i = start; i < mobile.length(); i++) {
            seq = seq.substring(1) + mobile.charAt(i);
            if (numbers.indexOf(seq) > -1 || descending.indexOf(seq) > -1) {
                editText.setError(validationHolder.getMobileValidator().getRegExMessage());
                editText.setSelectAllOnFocus(true);
                editText.requestFocus();
                return false;
            }
        }

        return isValid;
    }
    public boolean isMobileNumberValidWithIsdCode(EditText editText){
        boolean isValid = true;
        if(!validationHolder.getMobileValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getMobileValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        String mobile  = editText.getText().toString();
        String repeatedRegex =  ".*(.)\\1{7,}.*";
        if(mobile.matches(repeatedRegex)){
            editText.setError(validationHolder.getMobileValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
            return false;
        }

        isValid = (editText.getText().length() >= 10 &&
                editText.getText().length() <= 18);
        if(!isValid){
            editText.setError(validationHolder.getMobileValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
            return false;
        }
        int len = 7;
        String numbers = "0123456789";
        String descending   = "9876543210";
        int start   = len - 1;
        String seq = "_" + mobile.substring(0,start);
        for (int i = start; i < mobile.length(); i++) {
            seq = seq.substring(1) + mobile.charAt(i);
            if (numbers.indexOf(seq) > -1 || descending.indexOf(seq) > -1) {
                editText.setError(validationHolder.getMobileValidator().getRegExMessage());
                editText.setSelectAllOnFocus(true);
                editText.requestFocus();
                return false;
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

    public boolean isOtpValid(EditText editText){
        if(!validationHolder.getOtpValidator().isOptional()){
            if(!isNotBlank(editText,validationHolder.getOtpValidator().getEmptyFieldMessage())) {
                return false;
            }
        }else{
            return true;
        }
        boolean isValid = true;
        isValid = editText.getText().length() == validationHolder.getOtpValidator().getMaxLength();
        if(!isValid){
            editText.setError(validationHolder.getOtpValidator().getRegExMessage());
            editText.setSelectAllOnFocus(true);
            editText.requestFocus();
        }
        return isValid;
    }


}
