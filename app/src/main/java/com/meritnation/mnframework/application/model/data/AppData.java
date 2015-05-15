package com.meritnation.mnframework.application.model.data;

import java.io.Serializable;

/**
 * This must be parent of all POJOs and Beans used in app.
 * Created by Hukum Singh on 27/4/15.
 */
public class AppData implements Serializable {
    /**
     * Error code thrown by REST API.
     */
    private int errorCode;
    /**
     * Error message thrown by REST API.
     */
    private String errorMessage;
    /**
     * It shows success
     */
    private boolean succeeded = true;

    /**
     *
     * @return true if API doesn't throw any errorCode and errorMessage, false otherwise.
     */
    public boolean isSucceeded() {
        return succeeded;
    }

    /**
     *
     * @return int error code if api thrown any
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code in app data.
     * @param errorCode int
     */
    public void setErrorCode(int errorCode) {
        this.succeeded = false;
        this.errorCode = errorCode;
    }

    /**
     *
     * @return String error message thrown by API.
     */
    public String getErrorMessage() {
        return errorMessage;

    }

    /**
     * Sets error message if thrown by API any.
     * @param errorMessage String
     */
    public void setErrorMessage(String errorMessage) {
        this.succeeded = false;
        this.errorMessage = errorMessage;
    }
}
