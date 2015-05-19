package com.meritnation.mnframework.modules.account.model.parser;


import com.meritnation.mnframework.application.model.data.AppData;
import com.meritnation.mnframework.application.model.parser.ApiParser;
import com.meritnation.mnframework.modules.account.model.data.LoginData;
import com.meritnation.mnframework.modules.constants.RequestTagConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hukum Singh on 27/4/15.
 */
public class AccountParser implements ApiParser {
    /**
     * Parse all responses for Account module.
     * @param apiResponse
     * @param requestTag
     * @return
     * @throws JSONException
     */
    @Override
    public AppData parseApiResponse(String apiResponse, String requestTag) throws JSONException {
        if(requestTag.equals(RequestTagConstant.LOGIN_REQUEST_TAG)) {
            return parseLoginResponse(apiResponse);
        }else if(requestTag.equals(RequestTagConstant.LOGOUT_REQUEST_TAG)){
            return parseLogOutData(apiResponse);
        }else {
            return null;
        }
    }

    private LoginData parseLoginResponse(String apiResponse)  throws JSONException{
        LoginData loginData = new LoginData();
        loginData.setResponse(apiResponse);
        JSONObject jsonObject = new JSONObject(apiResponse);
        loginData.setUserId(jsonObject.getString("uid"));
        return loginData;
    }

    private AppData parseLogOutData(String apiResponse) throws JSONException {
        return  new LoginData();
    }
}
