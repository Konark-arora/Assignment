package com.meritnation.mnframework.application.analytics;

import android.app.Activity;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.meritnation.mnframework.application.FrameworkApplication;
import com.meritnation.mnframework.application.FrameworkApplication.TrackerName;

/**
 * This is Google Analytics Manager class it will help you out to send events and screen views to google analytics server.
 * Created by Hukum Singh on 16/4/15.
 */
public class GAManager {
    /**
     * Here you can send Event to GA server.
     * @param activity
     * @param category
     * @param action
     * @param label
     */
    public static void sendTrackingEvent(Activity activity, String category, String action, String label) {
        Tracker t = ((FrameworkApplication) activity.getApplication()).getTracker(TrackerName.APP_TRACKER);
        t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }

    /**
     * Here you can send screen Views to GA server.
     * @param activity
     * @param screenName
     */
    public static void sendScreenView(Activity activity, String screenName) {
        // Get tracker.
        Tracker t = ((FrameworkApplication) activity.getApplication()).getTracker(TrackerName.APP_TRACKER);
        // Set screen name.
        t.setScreenName(screenName);
        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
