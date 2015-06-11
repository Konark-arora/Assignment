package com.truecaller.assignment.application.model.listener;

import org.json.JSONException;

/**
 * This is callback for api responses.
 */
public interface OnAPIResponseListener {
    /**
     *  When we get HTTP 200 as response Code.
     * @param requestTag
     */
    void onAPIResponse(String apiresponse, String requestTag);
    /**
     * When we get other then HTTP 200 as response Code.
     * @param message
     * @param requestTag
     */
    void onInternalServerError(String message, String requestTag);

    /**
     * When api get broken or response structure changes.
     * @param e
     * @param requestTag
     */
    void onAPIParsingException(JSONException e, String requestTag);
}
