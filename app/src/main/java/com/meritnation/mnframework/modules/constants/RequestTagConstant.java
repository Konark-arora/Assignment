package com.meritnation.mnframework.modules.constants;

/**
 * This constant class is related to RequestTags for sending request through volley lib. the advantage of request Tag is
 * you can cancel any rest api request any time before its completion and by using request tag you csn also distingues api
 * response based on request Tag.
 *
 * Created by Hukum Singh on 14/5/15.
 */
public class RequestTagConstant {
    //Account request tags START
    public static final String LOGIN_REQUEST_TAG = "LogInRequest";
    public static final String LOGOUT_REQUEST_TAG = "LogOutRequest";
    //Account request tags END
}
