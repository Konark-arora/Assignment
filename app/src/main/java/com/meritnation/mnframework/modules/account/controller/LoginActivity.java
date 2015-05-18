package com.meritnation.mnframework.modules.account.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.meritnation.mnframework.R;
import com.meritnation.mnframework.application.analytics.GAManager;
import com.meritnation.mnframework.application.constant.AnalyticsConstant;
import com.meritnation.mnframework.application.constant.RequestTagConstant;
import com.meritnation.mnframework.application.controller.BaseActivity;
import com.meritnation.mnframework.application.model.data.AppData;
import com.meritnation.mnframework.application.validator.FormValidatorBuilder;
import com.meritnation.mnframework.modules.account.model.data.LoginData;
import com.meritnation.mnframework.modules.account.model.listener.OnLoginListener;
import com.meritnation.mnframework.modules.account.model.listener.OnLogoutListener;
import com.meritnation.mnframework.modules.account.model.manager.AccountManager;
import com.meritnation.mnframework.modules.account.model.parser.AccountParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

/**
 * This is demo activity
 */
public class LoginActivity extends BaseActivity implements OnLoginListener, OnLogoutListener {

    boolean isLogin = false;
    private TextView response;
    private Button button;
    private String userId;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        response = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        GAManager.sendScreenView(this, AnalyticsConstant.CATEGORY_LOGIN_SCREEN);
    }

    public void getDataFromServer(View v) {
        if (FormValidatorBuilder.buildAppFormValidator(this).isMobileNumberValid(editText)) {
            GAManager.sendTrackingEvent(this, AnalyticsConstant.CATEGORY_LOGIN_SCREEN, AnalyticsConstant.ACTION_LOGIN, AnalyticsConstant.LABEL_LOGIN);
        }
        AccountManager accountManager = new AccountManager(new AccountParser(), this);
        if (isLogin) {
            showProgressDialog("Logging out ...");
            accountManager.logOut(RequestTagConstant.LOGOUT_REQUEST_TAG, this, userId);
        } else {
            showProgressDialog("Logging in ...");
            accountManager.login(RequestTagConstant.LOGIN_REQUEST_TAG, "hukum@bhavna.com", "1234");
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
        hideProgressDialog();
        if (requestTag.equals(RequestTagConstant.LOGIN_REQUEST_TAG)) {
            handleLoginResponse(appData);
        } else if (requestTag.equals(RequestTagConstant.LOGOUT_REQUEST_TAG)) {
            handleLogoutResponse(appData);
        }
    }

    private void handleLoginResponse(AppData appData) {
        if (appData.isSucceeded()) {
            LoginData loginData = (LoginData) appData;
            onLoginSuccess(loginData);
        } else {
            onLoginFailed(appData.getErrorMessage());
        }
    }

    private void handleLogoutResponse(AppData appData) {
        if (appData.isSucceeded()) {
            onLogoutSuccess();
        }
    }

    @Override
    public void onLoginSuccess(LoginData loginData) {
        response.setText(loginData.getResponse());
        button.setText("Log out");
        userId = loginData.getUserId();
        isLogin = true;
        try {
            JSONObject loginJsonObject = new JSONObject(loginData.getResponse());
            AccountManager accountManager = new AccountManager(getHelper().getLoginTableDao());
            accountManager.writeUserDataInDatabse(loginJsonObject);
            showLongToast("user saved in Db");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            showLongToast("user already exist in Db");
        }
    }

    @Override
    public void onLoginFailed(String message) {

    }

    @Override
    public void onLogoutSuccess() {
        button.setText("Log in");
        response.setText("Logged Out");
        isLogin = false;
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        openActivity(HelloNoBase.class, bundle);
    }

    @Override
    public void onLogoutFailed(String message) {

    }

    /**
     * When we get other then HTTP 200 as response Code.
     *
     * @param message
     * @param requestTag
     */
    @Override
    public void onInternalServerError(String message, String requestTag) {
        showLongToast(message);
        hideProgressDialog();
    }

    /**
     * When api get broken or response structure changes.
     *
     * @param e
     * @param requestTag
     */
    @Override
    public void onAPIParsingException(JSONException e, String requestTag) {
        showLongToast(e.getMessage());
        hideProgressDialog();
    }
}
