package com.meritnation.mnframework.application.model.manager;

import android.content.Context;

import com.meritnation.mnframework.application.model.data.AppData;
import com.meritnation.mnframework.application.model.listener.OnAPIResponseListener;
import com.meritnation.mnframework.application.model.parser.ApiParser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * This main manager class. Every module manager must extend this class, to use its common functionality.
 *  Like make webservice call using GET POST. and return data from API in AddData dataType. you can also write
 *  pojo OR Bean objects to internal file system using Serialization technique of JAVA.
 * Created by Hukum on 12/5/15.
 */
public abstract class Manager implements WebServiceRules, SerializeAndDeserializePOJORules {

    private WebService webService;
    private SerializeAndDeserializePOJO serializeAndDeserializePOJO;

    /**
     * We must use this constructor if we want to use WebService feature of manager.
     * @param apiParser
     * @param onApiResponseListener
     */
    public Manager(ApiParser apiParser, OnAPIResponseListener onApiResponseListener) {
        webService = new WebService(apiParser, onApiResponseListener);
        serializeAndDeserializePOJO = new SerializeAndDeserializePOJO();
    }

    /**
     * We must use this constructor if we want to serialise POJOs or beans.
     */
    public Manager(){
        serializeAndDeserializePOJO = new SerializeAndDeserializePOJO();
    }
    /**
     * This method will make GET Request on WebAPI.
     * @param apiUrl
     * @param requestHeader
     * @param requestTag
     */
    @Override
    public void getData(String apiUrl, Map<String, String> requestHeader, String requestTag) {
        if(webService == null){
           throw new IllegalStateException(WebService.WEB_SERVICE_INITIALISATION_EXCEPTION_MESSAGE);
        }else {
            webService.getData(apiUrl, requestHeader, requestTag);
        }
    }

    /**
     * This method will make POST Request on WebAPI.
     * @param apiUrl
     * @param requestHeader
     * @param postParams
     * @param requestTag
     */
    @Override
    public void postData(String apiUrl, Map<String, String> requestHeader, Map<String, String> postParams, String requestTag) {
        if(webService == null){
            throw new IllegalStateException(WebService.WEB_SERVICE_INITIALISATION_EXCEPTION_MESSAGE);
        } else {
            webService.postData(apiUrl, requestHeader, postParams, requestTag);
        }
    }

    /**
     * This method will make POST multipart request on WebAPI and will upload file over server.
     * @param apiUrl
     * @param requestHeader
     * @param postParams
     * @param file
     * @param requestTag
     */
    @Override
    public void uploadFile(String apiUrl, Map<String, String> requestHeader, Map<String, String> postParams, File file, String requestTag) {
        if(webService == null){
            throw new IllegalStateException(WebService.WEB_SERVICE_INITIALISATION_EXCEPTION_MESSAGE);
        } else {
            webService.uploadFile(apiUrl, requestHeader, postParams, file, requestTag);
        }
    }

    /**
     * You may cancel any request any time using its RequestTag.
     * @param requestTag
     */
    @Override
    public void cancelRequestByTag(String requestTag) {
        if(webService == null){
            throw new IllegalStateException(WebService.WEB_SERVICE_INITIALISATION_EXCEPTION_MESSAGE);
        }else {
            webService.cancelRequestByTag(requestTag);
        }
    }

    /**
     * This method returns de-serialized object. user should cast it into appropriate type.
     *
     * @param context
     * @param serializedFileName key to retrieve particular object.
     * @return user object in the form of Object.class, user should cast it.
     * @throws IOException
     */
    @Override
    public AppData getDeSerializedBeanObject(Context context, String serializedFileName) throws IOException {
        return serializeAndDeserializePOJO.getDeSerializedBeanObject(context,serializedFileName);
    }

    /**
     * This method save user object to internal memory (in the form of File system) of application.
     *
     * @param context
     * @param serializedFileName Key to save a Object into file system.
     * @param beanObject         Object-value to save in file syetem.
     * @throws IOException
     */
    @Override
    public void saveSerializedBeanObject(Context context, String serializedFileName, AppData beanObject) throws IOException {
        serializeAndDeserializePOJO.saveSerializedBeanObject(context,serializedFileName,beanObject);
    }

    /**
     * You may clean file by giving its name.
     * @param context
     * @param serializedFileName
     */
    @Override
    public boolean cleanSerializedFile(Context context, String serializedFileName) {
        return serializeAndDeserializePOJO.cleanSerializedFile(context,serializedFileName);
    }
}
