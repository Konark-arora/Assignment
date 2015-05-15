package com.meritnation.mnframework.application.model.parser;

import com.meritnation.mnframework.application.model.data.AppData;

import org.json.JSONException;

/**
 * Created by Hukum Singh on 27/4/15.
 */
public interface ApiParser {
    /**
     * This is common rule for parsing.
     * @param apiResponse
     * @param requestTag
     * @return
     * @throws JSONException
     */
    AppData parseApiResponse(String apiResponse, String requestTag) throws JSONException;
}
