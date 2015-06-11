
package com.truecaller.assignment.application.validator;

/**
 */
class ValidationHolder {
    private EditTextValidator mobileValidator;
    private EditTextValidator emailValidator;

    public EditTextValidator getMobileValidator() {
        return mobileValidator;
    }

    public void setMobileValidator(EditTextValidator mobileValidator) {
        this.mobileValidator = mobileValidator;
    }

    public EditTextValidator getEmailValidator() {
        return emailValidator;
    }

    public void setEmailValidator(EditTextValidator emailValidator) {
        this.emailValidator = emailValidator;
    }
}
