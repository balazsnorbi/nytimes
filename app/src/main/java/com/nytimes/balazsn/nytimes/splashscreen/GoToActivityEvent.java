package com.nytimes.balazsn.nytimes.splashscreen;

import android.app.Activity;

public final class GoToActivityEvent {
    private Class<? extends Activity> mNextClass;

    public GoToActivityEvent(Class<? extends Activity> theClass) {
        mNextClass = theClass;
    }

    public Class<? extends Activity> getmNextClass() {
        return mNextClass;
    }
}
