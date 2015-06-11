package com.truecaller.assignment.modules.exam.controller;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by konark on 11/6/15.
 */
public class WordsCount implements ProblemStatementInterface {

    private String dataResponse;

    public WordsCount(String response){
        this.dataResponse = response;
    }

    @Override
    public Map<String, Integer> getData() {

        Map<String, Integer> occurrences = new LinkedHashMap<String, Integer>();

        // Split string into words after every space or newline
        String[] tokens = dataResponse.split(" |\\\n");

        for (String word : tokens) {
            Integer oldCount = occurrences.get(word);
            if (oldCount == null) {
                oldCount = 0;
            }
            occurrences.put(word, oldCount + 1);
        }

        return occurrences;
    }
}