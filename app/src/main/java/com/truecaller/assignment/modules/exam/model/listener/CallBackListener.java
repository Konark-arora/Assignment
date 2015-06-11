package com.truecaller.assignment.modules.exam.model.listener;

/**
 * This is the callback listener
 */
public interface CallBackListener {
    /**
     * @param apiResponse
     */
        void onSuccess(String apiResponse);

    /**
     * if login is not successful
     * @param message from api
     */
        void onFailed(String message);
    }