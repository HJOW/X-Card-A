package org.duckdns.hjow.xcard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;

/** 자바스크립트에서 XCardInterface 이름으로 이 객체에 액세스하여, 아래 메소드들을 호출할 수 있음 */
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
        if(status.equals("y") || status.equals("true" )) { advertisement = true;  return; }
        if(status.equals("n") || status.equals("false")) { advertisement = false; return; }
        throw new RuntimeException("Wrong boolean character " + status);
    }

    @JavascriptInterface
    public String getBuildNumber() {
        return String.valueOf(3);
    }

    @JavascriptInterface
    public void openURL(String url) {
        if(! (url.startsWith("http://") || url.startsWith("https://"))) url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        ctx.startActivity(browserIntent);
    }
}
