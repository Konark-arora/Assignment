package com.meritnation.mnframework.modules.account.model.manager;

import android.content.Context;
import android.os.Build;

import com.meritnation.mnframework.application.constant.RequestParamConstant;
import com.meritnation.mnframework.application.constant.URLConstant;
import com.meritnation.mnframework.application.model.listener.OnAPIResponseListener;
import com.meritnation.mnframework.application.model.manager.Manager;
import com.meritnation.mnframework.application.model.parser.ApiParser;
import com.meritnation.mnframework.application.utilities.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This is Account(Demo Mudule)Manager in the same way you can create your own module manger.
 * Created by Hukum Singh on 27/4/15.
 */
public class AccountManager extends Manager {


    /**
     * This is public constructor of AccountManger
     * @param apiParser
     * @param onApiResponseListener
     */
    public AccountManager(ApiParser apiParser, OnAPIResponseListener onApiResponseListener) {
        super(apiParser, onApiResponseListener);
    }

    /**
     * It will login user into meritnation.com and will retur userId and other user basic info.
     * @param requestTag
     * @param userName
     * @param password
     */
    public void login(String requestTag,String userName, String password){
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userName);
        params.put("password", password);
        params.put("login", "1");
        postData(URLConstant.LOGIN, null, params, requestTag);
    }

    /**
     * It will logout user from meritnation.com
     * @param requestTag
     * @param context
     * @param userId
     */
    public void logOut(String requestTag ,Context context, String userId) {
        String deviceId = Utils.getDeviceId(context);
        Map<String, String> params = new HashMap<String, String>();
        params.put(RequestParamConstant.API_PARAM_USER_ID, userId);
        params.put(RequestParamConstant.API_PARAM_APP_ID, context.getPackageName());
        params.put(RequestParamConstant.API_PARAM_USER_INFO_DEVICE_ID, deviceId);
        params.put(RequestParamConstant.API_PARAM_USER_INFO_SOURCE, "1");
        params.put(RequestParamConstant.API_PARAM_USER_OS_VERSION, "Android " + Build.VERSION.RELEASE);
        params.put(RequestParamConstant.API_PARAM_USER_APP_VERSION, Utils.getAppVersion(context));
        if (Utils.isTablet(context))
            params.put(RequestParamConstant.API_PARAM_USER_DEVICE_TYPE, "Tablet");
        else
            params.put(RequestParamConstant.API_PARAM_USER_DEVICE_TYPE, "Phone");
        params.put(RequestParamConstant.API_PARAM_USER_ISLOGOUT, "" + 1);
        params.put(RequestParamConstant.API_PARAM_USER_PUSH_NOTIFICATION_STATUS, "" + 0);

        postData(URLConstant.LOGOUT_USER, null, params, requestTag);
    }
}
