package com.truecaller.assignment.modules.exam.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.truecaller.assignment.R;
import com.truecaller.assignment.application.controller.BaseActivity;
import com.truecaller.assignment.modules.constants.RequestTagConstant;
import com.truecaller.assignment.modules.exam.model.listener.CallBackListener;
import com.truecaller.assignment.modules.exam.model.manager.TrueCallerAssignmentManager;

import java.util.Map;

/**
 * Created by konark on 11/6/15.
 */

public class TrueCallerAssignmentActivity extends BaseActivity implements CallBackListener {

    private Button AssignmentButton;
    private TextView textView10thCharacter;
    private TextView textViewEvery10thCharacter;
    private TextView textViewWordCounter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_caller_assignment);
        setupToolbar();
        getLayoutElementViewsById();
    }

    /**
     * Method used for setting up toolbar.
     */
    public void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.ripple_toolbar);

        // Inflate the title to be displayed in the toolbar
        toolbar.setTitle("Assignment Activity");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    /*
   * Get layout elements For view used in XML
   */
    private void getLayoutElementViewsById() {
        textView10thCharacter = (TextView) findViewById(R.id.text_view);
        textViewEvery10thCharacter = (TextView) findViewById(R.id.text_view_every_10th_character);
        textViewWordCounter = (TextView) findViewById(R.id.text_view_word_counter);
        AssignmentButton = (Button) findViewById(R.id.assignment_button);
    }

    /*
   * Function called for calling html view from given url and is called from xml button click
   */
    public void getDataFromServer(View view) {
        showProgressDialog("Loading data for you..");
        TrueCallerAssignmentManager networkCall = new TrueCallerAssignmentManager();
        networkCall.addListener(this);
        networkCall.getTrueCallerData(RequestTagConstant.TRUECALLER_ASSIGNMENT_TAG);
    }

    /*
     * Successful callback from the server
     */
    @Override
    public void onSuccess(String response) {
        hideProgressDialog();

        TrueCallerDataStructureFactory dataParsedPerProblemStatement = new TrueCallerDataStructureFactory();

        // Get an object of find10thChar from the Problem Statement Data Structure factory class.
        Find10thCharacter find10thChar = (Find10thCharacter)(dataParsedPerProblemStatement.getDataStructureType("find10thChar", response));
        StringBuilder tengthChar = find10thChar.getData();

        //get a map for every10thChar in the response from the Problem Statement Data Structure factory class.
        Every10thCharacter every10thCharacter = (Every10thCharacter)(dataParsedPerProblemStatement.getDataStructureType("every10thChar", response));
        Map every10thCharMap = every10thCharacter.getData();

        //get a map for words occurences in the response from the Problem Statement Data Structure factory class.
        WordsCount wordsOccurencesCount = (WordsCount)(dataParsedPerProblemStatement.getDataStructureType("wordsCount", response));
        Map occurrences = wordsOccurencesCount.getData();

        String everyTengthChar = "Sorted HashMap with index and its value returned from callback";
        String wordsCount = "HashMap with words and their callbacks returned from response";

        textView10thCharacter.setText(tengthChar);
        textViewEvery10thCharacter.setText(everyTengthChar);
        textViewWordCounter.setText(wordsCount);
    }

    /*
     * Failure callback from the server
     */
    @Override
    public void onFailed(String message) {
        hideProgressDialog();
    }
}