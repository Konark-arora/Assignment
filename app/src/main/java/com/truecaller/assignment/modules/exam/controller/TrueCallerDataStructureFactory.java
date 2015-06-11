package com.truecaller.assignment.modules.exam.controller;

/**
 * Created by konark on 11/6/15.
 */


public class TrueCallerDataStructureFactory {

    /*
   * Factory Design Pattern being used here for each problem statement
   */
    //use getData method to get object of type problemStatement
    public Object getDataStructureType(String problemStatement,String response){
        if(problemStatement == null){
            return null;
        }
        if(problemStatement.equalsIgnoreCase("find10thChar")){
            return new Find10thCharacter(response);

        } else if(problemStatement.equalsIgnoreCase("every10thChar")){
            return new Every10thCharacter(response);

        } else if(problemStatement.equalsIgnoreCase("wordsCount")){
            return new WordsCount(response);
        }

        return null;
    }
}