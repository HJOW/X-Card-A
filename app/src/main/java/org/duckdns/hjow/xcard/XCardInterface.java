package org.duckdns.hjow.xcard;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class XCardInterface {
    Context ctx;
    MainActivity activity;
    public XCardInterface(Context ctx, MainActivity activity) {
        this.ctx = ctx;
        this.activity = activity;
    }

    @JavascriptInterface
    public String getPlatform() {
        return "Android";
    }

    @JavascriptInterface
    public void exit() {
        Log.d("XCardInterface", "Exit called");
        activity.finish();
    }
}
