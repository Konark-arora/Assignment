package com.truecaller.assignment.modules.exam.model.parser;

import com.truecaller.assignment.application.model.data.AppData;
import com.truecaller.assignment.application.model.parser.ApiParser;
import com.truecaller.assignment.modules.constants.RequestTagConstant;

import org.json.JSONException;

/**
 */
public class TrueCallerAssignmentParser implements ApiParser {
    /**
     * Parse all responses for TrueCallerAssignmentParser module.
     * @param apiResponse
     * @param requestTag
     * @return
     * @throws JSONException
     */
    @Override
    public AppData parseApiResponse(String apiResponse, String requestTag) throws JSONException {
        if(requestTag.equals(RequestTagConstant.TRUECALLER_ASSIGNMENT_TAG)) {
            AppData data = new AppData();
            data.setData(apiResponse);
            return data;
        }else {
            return null;
        }
    }

}
