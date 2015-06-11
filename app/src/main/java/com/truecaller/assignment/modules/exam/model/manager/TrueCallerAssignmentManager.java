package com.truecaller.assignment.modules.exam.model.manager;

import android.support.annotation.NonNull;

import com.j256.ormlite.dao.Dao;
import com.truecaller.assignment.application.model.listener.OnAPIResponseListener;
import com.truecaller.assignment.application.model.manager.Manager;
import com.truecaller.assignment.application.model.parser.ApiParser;
import com.truecaller.assignment.modules.constants.RequestTagConstant;
import com.truecaller.assignment.modules.constants.URLConstant;
import com.truecaller.assignment.modules.exam.model.listener.CallBackListener;
import com.truecaller.assignment.modules.exam.model.parser.TrueCallerAssignmentParser;

import org.json.JSONException;

/**
 * This is Manager in the same way you can create your own module manger.
 */
public class TrueCallerAssignmentManager extends Manager implements OnAPIResponseListener {

    private CallBackListener callBackListener;

    /**
     * This is public constructor of AccountManger for web service purpose.
     * @param apiParser
     * @param onApiResponseListener
     */
    public TrueCallerAssignmentManager(ApiParser apiParser, OnAPIResponseListener onApiResponseListener) {
        super(apiParser, onApiResponseListener);
    }

    /**
     * This is public constructor of TrueCallerAssignmentManager for Database purpose.
     * @param dao
     */
    public TrueCallerAssignmentManager(Dao dao){
            super(dao);
    }

    /**
     * We must use this constructor if we want to serialise POJOs or beans.
     */
    public TrueCallerAssignmentManager() {
    }

    public void addListener(@NonNull CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    public void getTrueCallerData(String requestTag){
        initWebService(new TrueCallerAssignmentParser(), this);
        getData(URLConstant.TRUE_CALLER_URL,null,requestTag);
    }

    @Override
    public void onAPIResponse(String response, String requestTag) {
        if(requestTag.equals(RequestTagConstant.TRUECALLER_ASSIGNMENT_TAG)){
            callBackListener.onSuccess(response);
        }
    }

    @Override
    public void onInternalServerError(String message, String requestTag) {

    }

    @Override
    public void onAPIParsingException(JSONException e, String requestTag) {

    }
}
