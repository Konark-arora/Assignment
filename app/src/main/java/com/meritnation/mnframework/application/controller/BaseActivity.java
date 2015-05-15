package com.meritnation.mnframework.application.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * This is BaseActivity for all activities of app. Here we have defined some common operation
 * we usually perform like showToast and showProgress Dialog with proper message and openActivity
 * and openActivityForResults. This is very useful when we want to change whole app's activities.
 * For example from ActionBarActivity to FragmentActivity or Activity to ActionBarActivity.
 * Created by Hukum Singh on 14/5/15.
 */
public class BaseActivity extends ActionBarActivity {
    private ProgressDialog progressDialog;

    /**
     * Show progress dialog with passed message.
     * @param message
     */
    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    /**
     * Hide progress dialog with proper handling of exception related to context leak.
     */
    public void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {

        } finally {
            progressDialog = null;
        }
    }

    /**
     * Shows Toast message for long Time.
     * @param toastMessage
     */
    public void showLongToast(String toastMessage) {
        if (toastMessage != null && toastMessage.length() > 0)
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
    }

    /**
     * Shows Toast message for short period of time.
     * @param toastMessage
     */
    public void showShortToast(String toastMessage) {
        if (toastMessage != null && toastMessage.length() > 0)
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * open tagetActivity with passed bundle if we pass bundle as null it won't send bundle at all.
     * @param targetActivity
     * @param bundle
     */
    public void openActivity(Class targetActivity, Bundle bundle){
        Intent intent = new Intent(this, targetActivity);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * open tagetActivity with requestCode and passed bundle if we pass bundle as null it won't send bundle at all.
     * @param targetActivity
     * @param bundle
     * @param requestCode
     */
    public void openActivityForResult(Class targetActivity, Bundle bundle, int requestCode){
        Intent intent = new Intent(this, targetActivity);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
