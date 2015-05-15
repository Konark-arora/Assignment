package com.meritnation.mnframework.modules.account.model.data;


import com.meritnation.mnframework.application.model.data.AppData;

/**
 * Created by Hukum on 27/4/15.
 */
public class LoginData extends AppData {
    private String response;
    private String userId;

    /**
     *
     * @return String UserId of user who recently login in the app.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set userId of user who are logging in app.
     * @param userId String
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return JSON response from server when we try to login.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Set JSON response which we got from login API.
     * @param response
     */
    public void setResponse(String response) {
        this.response = response;
    }
}
