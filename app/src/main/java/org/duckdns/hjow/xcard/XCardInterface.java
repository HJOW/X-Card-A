package org.duckdns.hjow.xcard;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class XCardInterface {
    Context ctx;
    MainActivity activity;
    boolean advertisement = true;
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

    @JavascriptInterface
    public String isAdvertisementActive() {
        return advertisement == true ? "Y" : "N";
    }

    @JavascriptInterface
    public void setAd(String status) {
        if(status == null) status = "N";
        status = status.toLowerCase().trim();
        if(status.equals("y") || status.equals("true" )) advertisement = true;
        if(status.equals("n") || status.equals("false")) advertisement = false;
        throw new RuntimeException("Wrong boolean character " + status);
    }
}
