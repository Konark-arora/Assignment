package com.meritnation.mnframework.application.model.manager;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.MultiPartRequest;
import com.android.volley.toolbox.StringRequest;
import com.meritnation.mnframework.application.FrameworkApplication;
import com.meritnation.mnframework.application.model.data.AppData;
import com.meritnation.mnframework.application.model.listener.OnAPIResponseListener;
import com.meritnation.mnframework.application.model.parser.ApiParser;
import com.meritnation.mnframework.application.utilities.Utils;

import org.json.JSONException;

import java.io.File;
import java.util.Map;

/**
 * This class helps user to make GET and POST request on REST API.
 * Created by Hukum Singh on 27/4/15.
 */
class WebService implements WebServiceRules {

    private Context context;
    private ApiParser apiParser;
    private OnAPIResponseListener onApiResponseListener;
    private String requestTag;
    public static final String WEB_SERVICE_INITIALISATION_EXCEPTION_MESSAGE = "Initialize webService first, using another constructor of manager";

    /**
     * This is public constructor of WebService.
     * @param apiParser
     * @param onApiResponseListener
     */
    public WebService(ApiParser apiParser, OnAPIResponseListener onApiResponseListener) {
        this.context = FrameworkApplication.getInstance().getApplicationContext();
        this.apiParser = apiParser;
        this.onApiResponseListener = onApiResponseListener;
    }

    /**
     * This method will make GET Request on WebAPI.
     * @param apiUrl
     * @param requestHeader
     * @param requestTag
     */
    @Override
    public void  getData(String apiUrl, final Map<String, String> requestHeader, String requestTag){
        this.requestTag = requestTag;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, responseOk, responseNotOk) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(requestHeader != null) {
                    return requestHeader;
                }else {
                    return Utils.getApiRequestHeader(context);
                }
            }
        };
        //Setting should not be cached, now json response will not be cached.
        stringRequest.setShouldCache(false);
        // Adding request to request queue
        FrameworkApplication.getInstance().addToRequestQueue(stringRequest, requestTag);
    }

    /**
     *
     * @param apiUrl
     * @param requestHeader
     * @param postParams
     * @param requestTag
     */
    @Override
    public void postData(String apiUrl, final Map<String, String> requestHeader,final Map<String, String> postParams, String requestTag){
        this.requestTag = requestTag;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiUrl, responseOk, responseNotOk) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(requestHeader != null) {
                    return requestHeader;
                }else {
                    return Utils.getApiRequestHeader(context);
                }
            }
            @Override
            protected Map<String, String> getParams() {
                return postParams;
            }
        };
        //Setting should not be cached, now json response will not be cached.
        stringRequest.setShouldCache(false);
        // Adding request to request queue
        FrameworkApplication.getInstance().addToRequestQueue(stringRequest, requestTag);
    }

    /**
     *
     * @param apiUrl
     * @param requestHeader
     * @param postParams
     * @param file
     * @param requestTag
     */
    @Override
    public void uploadFile(String apiUrl,final Map<String, String> requestHeader, Map<String, String> postParams, File file, String requestTag){
        this.requestTag = requestTag;
        MultiPartRequest uploadRequest = new MultiPartRequest(apiUrl, responseOk, responseNotOk,file, postParams) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(requestHeader != null) {
                    return requestHeader;
                }else {
                    return Utils.getApiRequestHeader(context);
                }
            }
        };
        //Setting should not be cached, now json response will not be cached.
        uploadRequest.setShouldCache(false);
        // Adding request to request queue
        FrameworkApplication.getInstance().addToRequestQueue(uploadRequest, requestTag);
    }

    /**
     *
     * @param requestTag
     */
    @Override
    public void cancelRequestByTag(String requestTag){
        FrameworkApplication.getInstance().cancelPendingRequests(requestTag);
    }

    private Response.Listener<String> responseOk = new Response.Listener<String>() {

        /**
         * Called when a response is received.
         *
         * @param response
         */
        @Override
        public void onResponse(String response) {
            try {
                AppData appData = apiParser.parseApiResponse(response, requestTag);
                onApiResponseListener.onAPIResponse(appData, requestTag);
            } catch (JSONException e) {
                onApiResponseListener.onAPIParsingException(e, requestTag);
            }catch (Exception e){
                onApiResponseListener.onAPIResponse(null, requestTag);
            }
        }
    };

    private Response.ErrorListener responseNotOk = new Response.ErrorListener() {

        /**
         * Callback method that an error has been occurred with the
         * provided error code and optional user-readable message.
         *
         * @param error
         */
        @Override
        public void onErrorResponse(VolleyError error) {
            if (onApiResponseListener == null)
                return;
            onApiResponseListener.onInternalServerError("" + error.getMessage() , requestTag);
        }
    };
}
