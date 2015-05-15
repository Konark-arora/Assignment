package com.meritnation.mnframework.application.model.manager;

import java.io.File;
import java.util.Map;

/**
 * Created by Hukum Singh on 13/5/15.
 */
public interface WebServiceRules {
    void getData(String apiUrl, final Map<String, String> requestHeader, String requestTag);
    void postData(String apiUrl, final Map<String, String> requestHeader, final Map<String, String> postParams, String requestTag);
    void uploadFile(String apiUrl, final Map<String, String> requestHeader, Map<String, String> postParams, File file, String requestTag);
    void cancelRequestByTag(String requestTag);
}
