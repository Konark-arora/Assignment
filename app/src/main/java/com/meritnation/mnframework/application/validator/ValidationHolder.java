/*
 * Copyright (c) 2015. This is under copyright of Applect Learning System pvt. ltd.
 * All rights are reserved.
 */

package com.meritnation.mnframework.application.validator;

/**
 * Created by Hukum Singh on 27/4/15.
 */
class ValidationHolder {
    private EditTextValidator mobileValidator;
    private EditTextValidator registerPasswordValidator;
    private EditTextValidator confirmPasswordValidator;
    private EditTextValidator confirmPasswordNotMatchValidator;
    private EditTextValidator emailValidator;
    private EditTextValidator loginPasswordValidator;
    private EditTextValidator loginUserNameValidator;
    private EditTextValidator otpValidator;

    public EditTextValidator getMobileValidator() {
        return mobileValidator;
    }

    public void setMobileValidator(EditTextValidator mobileValidator) {
        this.mobileValidator = mobileValidator;
    }

    public EditTextValidator getRegisterPasswordValidator() {
        return registerPasswordValidator;
    }

    public void setRegisterPasswordValidator(EditTextValidator registerPasswordValidator) {
        this.registerPasswordValidator = registerPasswordValidator;
    }

    public EditTextValidator getEmailValidator() {
        return emailValidator;
    }

    public void setEmailValidator(EditTextValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    public EditTextValidator getLoginPasswordValidator() {
        return loginPasswordValidator;
    }

    public void setLoginPasswordValidator(EditTextValidator loginPasswordValidator) {
        this.loginPasswordValidator = loginPasswordValidator;
    }

    public EditTextValidator getLoginUserNameValidator() {
        return loginUserNameValidator;
    }

    public void setLoginUserNameValidator(EditTextValidator loginUserNameValidator) {
        this.loginUserNameValidator = loginUserNameValidator;
    }

    public EditTextValidator getConfirmPasswordValidator() {
        return confirmPasswordValidator;
    }

    public void setConfirmPasswordValidator(EditTextValidator confirmPasswordValidator) {
        this.confirmPasswordValidator = confirmPasswordValidator;
    }

    public EditTextValidator getConfirmPasswordNotMatchValidator() {
        return confirmPasswordNotMatchValidator;
    }

    public void setConfirmPasswordNotMatchValidator(EditTextValidator confirmPasswordNotMatchValidator) {
        this.confirmPasswordNotMatchValidator = confirmPasswordNotMatchValidator;
    }

    public EditTextValidator getOtpValidator() {
        return otpValidator;
    }

    public void setOtpValidator(EditTextValidator otpValidator) {
        this.otpValidator = otpValidator;
    }
}
