/*
 * Copyright (c) 2015. This is under copyright of Applect Learning System pvt. ltd.
 * All rights are reserved.
 */

package com.meritnation.mnframework.application.validator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.meritnation.mnframework.application.utilities.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Hukum on 9/12/14.
 */
public class FormValidatorBuilder {

    public static final String JSON_KEY_EMAIL_ID = "emailId";
    public static final String JSON_KEY_MOBILE_NUMBER = "mobileNumber";
    public static final String JSON_KEY_REG_PASSWORD = "registerPassword";
    public static final String JSON_KEY_CONF_PASSWORD = "confirmPassword";
    public static final String JSON_KEY_CONF_PASSWORD_NOT_MATCH = "confirmPasswordNotMatch";
    public static final String JSON_KEY_minLength = "minLength";
    public static final String JSON_KEY_maxLength = "maxLength";
    public static final String JSON_KEY_regExp = "regExp";
    public static final String JSON_KEY_isOptional = "isOptional";
    public static final String JSON_KEY_validationMessageForRegEx = "validationMessageForRegEx";
    public static final String JSON_KEY_validationMessageForOptional = "validationMessageForOptional";
    public static final String JSON_KEY_LOGIN_PASSWORD = "loginPassword";
    public static final String JSON_KEY_LOGIN_USERNAME = "loginUsername";
    public static final String JSON_KEY_OTP_PIN = "otpPin";
    private static final String CACHE_FILE_NAME = "validationRules";
    private static ProgressDialog progressDialog;

    public static AppFormValidatorRules buildAppFormValidator(Activity actContext) {
        if (actContext == null) {
            throw new IllegalArgumentException("FormValidatorBuilder : Context must not be null");
        }
        AppFormValidatorRules appFormValidatorRules = AppFormValidatorRules.getInstance();
        if (appFormValidatorRules == null) {
            appFormValidatorRules = buildAppFormValidatorRulesFromCache(actContext);
        }
        return appFormValidatorRules;
    }

    private static AppFormValidatorRules buildAppFormValidatorRulesFromCache(final Activity actContext) {

        showProgressDialog("Loading...", actContext);
        String validationJson = readJsonFromFileSystem(actContext);
        JSONObject response = null;
        if (validationJson != null) {
            try {
                response = new JSONObject(validationJson);
            } catch (JSONException e) {

            }
        }
        // end
        if (response != null) {
            initAppFormValidatorRulesSingleTon(actContext, response);
        }
        hideProgressDialog();
        return AppFormValidatorRules.getInstance();
    }

    private static void showProgressDialog(String message, Activity actContext) {
        progressDialog = new ProgressDialog(actContext);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private static String readJsonFromFileSystem(Activity actContext) {
        StringBuilder temp = new StringBuilder();
        try {
            FileInputStream fin = actContext.openFileInput(CACHE_FILE_NAME);
            int c;
            while ((c = fin.read()) != -1) {
                temp.append(Character.toString((char) c));
            }
        } catch (Exception e) {
            return null;
        }
        return temp.toString();
    }

    public static void initAppFormValidatorRulesSingleTon(Context actContext, JSONObject response) {
        ValidationHolder validationHolder = getValidationHolder(response);
        if (validationHolder != null) {
            AppFormValidatorRules.initialise(validationHolder);
            // write json in internal file system
            writeJsonInFileSystemForFutureUse(actContext, response.toString());
        }
    }

    private static void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {

        } finally {
            progressDialog = null;
        }
    }

    private static ValidationHolder getValidationHolder(JSONObject response) {
        ValidationHolder validationHolder = new ValidationHolder();
        try {
            JSONObject emailValidationJson = response.getJSONObject(JSON_KEY_EMAIL_ID);
            EditTextValidator emailValidator = getEditTextValidator(emailValidationJson);
            validationHolder.setEmailValidator(emailValidator);

            JSONObject mobileValidationJson = response.getJSONObject(JSON_KEY_MOBILE_NUMBER);
            EditTextValidator mobileValidator = getEditTextValidator(mobileValidationJson);
            validationHolder.setMobileValidator(mobileValidator);

            JSONObject otpValidationJson = response.getJSONObject(JSON_KEY_OTP_PIN);
            EditTextValidator otpValidator = getEditTextValidator(otpValidationJson);
            validationHolder.setOtpValidator(otpValidator);

            JSONObject passwordValidationJson = response.getJSONObject(JSON_KEY_REG_PASSWORD);
            EditTextValidator regPasswordValidator = getEditTextValidator(passwordValidationJson);
            validationHolder.setRegisterPasswordValidator(regPasswordValidator);

            JSONObject confPasswordValidationJson = response.getJSONObject(JSON_KEY_CONF_PASSWORD);
            EditTextValidator confPasswordValidator = getEditTextValidator(confPasswordValidationJson);
            validationHolder.setConfirmPasswordValidator(confPasswordValidator);

            JSONObject confPasswordNotMatchJson = response.getJSONObject(JSON_KEY_CONF_PASSWORD_NOT_MATCH);
            EditTextValidator confPasswordNotMatchValidator = getEditTextValidator(confPasswordNotMatchJson);
            validationHolder.setConfirmPasswordNotMatchValidator(confPasswordNotMatchValidator);

            JSONObject loginPasswordValidationJson = response.getJSONObject(JSON_KEY_LOGIN_PASSWORD);
            EditTextValidator loginPasswordValidator = getEditTextValidator(loginPasswordValidationJson);
            validationHolder.setLoginPasswordValidator(loginPasswordValidator);

            JSONObject loginUserNameValidationJson = response.getJSONObject(JSON_KEY_LOGIN_USERNAME);
            EditTextValidator loginUserNameValidator = getEditTextValidator(loginUserNameValidationJson);
            validationHolder.setLoginUserNameValidator(loginUserNameValidator);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return validationHolder;
    }

    private static void writeJsonInFileSystemForFutureUse(Context actContext, String string) {
        FileOutputStream outputStream;
        try {
            actContext.deleteFile(CACHE_FILE_NAME);
            outputStream = actContext.openFileOutput(CACHE_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static EditTextValidator getEditTextValidator(JSONObject jsonObject) throws JSONException {
        EditTextValidator editTextValidator = new EditTextValidator();
        editTextValidator.setMinLength(jsonObject.getInt(JSON_KEY_minLength));
        editTextValidator.setMaxLength(jsonObject.getInt(JSON_KEY_maxLength));
        editTextValidator.setOptional(jsonObject.getBoolean(JSON_KEY_isOptional));
        editTextValidator.setRegExMessage(jsonObject.getString(JSON_KEY_validationMessageForRegEx));
        editTextValidator.setEmptyFieldMessage(jsonObject.getString(JSON_KEY_validationMessageForOptional));
        editTextValidator.setRegExpression(jsonObject.optString(JSON_KEY_regExp));
        return editTextValidator;
    }

    public static void buildAppFormValidatorRulesFromNetwork(final Context actContext) {
        if (actContext == null) {
            throw new IllegalArgumentException("FormValidatorBuilder : Context must not be null");
        }
        // for time being we are reading file from assets but in future we will hit our validation
        // web service and set json response in following reference object "response".
        JSONObject response = getDefaultValidationRule(actContext);
        if (response != null) {
            FormValidatorBuilder.initAppFormValidatorRulesSingleTon(actContext, response);
        }
    }

    private static JSONObject getDefaultValidationRule(Context activity) {
        JSONObject response = null;
        String jsonRes = Utils.readFileFromAssetFolder(activity, "validationJson.txt");
        if (jsonRes != null) {
            try {
                response = new JSONObject(jsonRes);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
