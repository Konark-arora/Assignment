package com.truecaller.assignment.application.model.parser;

import com.truecaller.assignment.application.model.data.AppData;

import org.json.JSONException;

/**
 * Interface for API Parser
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
