package com.meritnation.mnframework.application.constants;

/**
 * This is constant class related to REST API Urls. Webservice domain can also be changed from here.
 * Created by Hukum Singh on 14/5/15.
 */
public class URLConstant {
    /**
     * This QA server
     */
    public static final String QA_SERVER = "http://happypotamus.com";
    /**
     * This is live server
     */
    public static final String LIVE_SERVER = "http://www.meritnation.com";
    /**
     * This is constant for Webservice domain.
     */
    public static final String DOMAIN_NAME = LIVE_SERVER;
    /**
     * Allows the user to signIn onto website after verifying credentials
     */
    public static final String LOGIN = DOMAIN_NAME +"/userapi/users/authenticate";
    /**
     * This api make user logout.
     */
    public static final String LOGOUT_USER = DOMAIN_NAME + "/mnapi/json/logout/";
}
