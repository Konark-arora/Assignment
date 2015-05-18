package com.meritnation.mnframework.modules.account.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.meritnation.mnframework.application.controller.BaseActivity;
import com.meritnation.mnframework.application.model.data.AppData;
import com.meritnation.mnframework.modules.account.model.manager.AccountManager;

import org.json.JSONException;

import java.sql.SQLException;

/**
 * Sample Android UI activity which displays a text window when it is run.
 * <p/>
 * <p>
 * <b>NOTE:</b> This does <i>not</i> extend the {@link OrmLiteBaseActivity} but instead manages the helper itself
 * locally using the {@link #databaseHelper} field, the {@link #getHelper()} private method, and the call to
 * {@link OpenHelperManager#releaseHelper()} inside of the {@link #onDestroy()} method.
 * </p>
 */
public class HelloNoBase extends BaseActivity {

    private final String LOG_TAG = getClass().getSimpleName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        String userId = getIntent().getStringExtra("userId");
        doDatabaseOperation(tv, userId);
        setContentView(tv);
    }


    /**
     * Do our sample database stuff as an example.
     */
    private void doDatabaseOperation(TextView tv, String userId) {
        try {
            AccountManager accountManager = new AccountManager(getHelper().getLoginTableDao());
            tv.setText(accountManager.getUserInfoFromDatabase(userId));
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Database exception", e);
            tv.setText("Database exeption: " + e);
            return;
        }
    }

    /**
     * When we get HTTP 200 as response Code.
     *
     * @param appData
     * @param requestTag
     */
    @Override
    public void onAPIResponse(AppData appData, String requestTag) {

    }

    /**
     * When we get other then HTTP 200 as response Code.
     *
     * @param message
     * @param requestTag
     */
    @Override
    public void onInternalServerError(String message, String requestTag) {

    }

    /**
     * When api get broken or response structure changes.
     *
     * @param e
     * @param requestTag
     */
    @Override
    public void onAPIParsingException(JSONException e, String requestTag) {

    }
}