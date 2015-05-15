package com.meritnation.mnframework.modules.account.model.listener;

import com.meritnation.mnframework.modules.account.model.data.LoginData;

/**
 * This is login callback listener
 */
public interface OnLoginListener {
    /**
     * if user got login successfully
     * @param loginData apiResponse
     */
        void onLoginSuccess(LoginData loginData);

    /**
     * if login is not successful
     * @param message from api
     */
        void onLoginFailed(String message);
    }