package com.meritnation.mnframework.modules.account.model.listener;

/**
 * This is logout callback listener
 */
public interface OnLogoutListener {
    /**
     * if user got logged out successfully
     */
        void onLogoutSuccess();

    /**
     * if user does not logged out successfully
     * @param message from logout api
     */
        void onLogoutFailed(String message);
    }