package com.truecaller.assignment.modules.exam.controller;

/**
 * Created by konark on 11/6/15.
 */
public class Find10thCharacter implements ProblemStatementInterface {

    private String dataResponse;

    public Find10thCharacter(String response){
        this.dataResponse = response;
    }

    @Override
    public StringBuilder getData() {
        StringBuilder tengthChar = new StringBuilder("Character on 10th position on response :: ");
        if (dataResponse != null) {
            tengthChar.append(dataResponse.charAt(9));
        }
        return tengthChar;
    }
}
