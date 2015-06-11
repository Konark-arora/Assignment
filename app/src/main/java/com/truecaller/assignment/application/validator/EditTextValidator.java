

package com.truecaller.assignment.application.validator;

/*
 * Validator for EditText that can be used for future text edits
 */
class EditTextValidator {
        private int minLength;
        private int maxLength;
        private boolean isOptional;
        private String emptyFieldMessage;
        private String regExMessage;
        private String regExpression;

        public int getMinLength() {
            return minLength;
        }

        public void setMinLength(int minLength) {
            this.minLength = minLength;
        }

        public int getMaxLength() {
            return maxLength;
        }

        public void setMaxLength(int maxLength) {
            this.maxLength = maxLength;
        }

        public boolean isOptional() {
            return isOptional;
        }

        public void setOptional(boolean isOptional) {
            this.isOptional = isOptional;
        }

        public String getEmptyFieldMessage() {
            return emptyFieldMessage;
        }

        public void setEmptyFieldMessage(String emptyFieldMessage) {
            this.emptyFieldMessage = emptyFieldMessage;
        }

        public String getRegExMessage() {
            return regExMessage;
        }

        public void setRegExMessage(String regExMessage) {
            this.regExMessage = regExMessage;
        }

        public String getRegExpression() {
            return regExpression;
        }

        public void setRegExpression(String regExpression) {
            this.regExpression = regExpression;
        }
    }