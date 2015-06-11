package com.truecaller.assignment.modules.exam.controller;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by konark on 11/6/15.
 */
public class Every10thCharacter implements ProblemStatementInterface {

    private String dataResponse;

    public Every10thCharacter(String response){
        this.dataResponse = response;
    }

    @Override
    public Map<Integer, Character> getData() {
        Map<Integer, Character> every10thCharMap = new LinkedHashMap<Integer, Character>();
        for (int i = 9; i < dataResponse.length(); i = i + 10) {
            every10thCharMap.put(i,dataResponse.charAt(i));
        }
        return every10thCharMap;
    }
}
