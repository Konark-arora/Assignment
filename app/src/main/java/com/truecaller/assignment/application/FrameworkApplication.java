package com.truecaller.assignment.application;

/**
 * Created by konark on 11/6/15.
 */

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import com.truecaller.assignment.R;
import com.truecaller.assignment.application.model.database.FrameworkDatabaseHelper;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

import java.util.HashMap;

/**
 * This is Application Class
 */
public class FrameworkApplication extends Application {

    private static FrameworkApplication sInstance;
    private Boolean isDebuggingEnabled = false;

    public static final String TAG = FrameworkApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;

    // Milli seconds per one minute
    private int ONE_MINUTE = 60000;
    private FrameworkDatabaseHelper databaseHelperInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //init validation singleton.
        // Database
        databaseHelperInstance = new FrameworkDatabaseHelper(this);
        try {
            isDebuggingEnabled = isDebuggable(this);
        } catch (Exception e) {
            isDebuggingEnabled = false;
        }
    }

    public FrameworkDatabaseHelper getDatabaseHelperInstance(){
        return databaseHelperInstance;
    }

    private boolean isDebuggable(Context ctx) {
        boolean debuggable = false;

        PackageManager pm = ctx.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(ctx.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags &= ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {

        }

        return debuggable;
    }

    public static FrameworkApplication getInstance() {
        return sInstance;
    }

    public boolean getDebuggingStatus() {
        return isDebuggingEnabled;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {

            DefaultHttpClient mDefaultHttpClient = new DefaultHttpClient();
            final ClientConnectionManager mClientConnectionManager = mDefaultHttpClient.getConnectionManager();
            final HttpParams mHttpParams = mDefaultHttpClient.getParams();
            final ThreadSafeClientConnManager mThreadSafeClientConnManager = new ThreadSafeClientConnManager( mHttpParams, mClientConnectionManager.getSchemeRegistry() );
            mDefaultHttpClient = new DefaultHttpClient( mThreadSafeClientConnManager, mHttpParams );
            final HttpStack httpStack = new HttpClientStack( mDefaultHttpClient );
            this.mRequestQueue = Volley.newRequestQueue(this.getApplicationContext(), httpStack);

            // Default Implementation.
           /* mRequestQueue = Volley.newRequestQueue(getApplicationContext());*/
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(new DefaultRetryPolicy(
                ONE_MINUTE,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    //=================GA TRACKING START=========
    // The following line should be changed to include the correct property id.
    private static final String PROPERTY_ID = "UA-61935186-1";

    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
                    : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker)
                    : analytics.newTracker(R.xml.ecommerce_tracker);
            t.enableAdvertisingIdCollection(true);
            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }
    //==============GA TRACKING END=============
}
