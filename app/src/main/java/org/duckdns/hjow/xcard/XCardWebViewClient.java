package org.duckdns.hjow.xcard;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.browser.customtabs.CustomTabsIntent;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class XCardWebViewClient extends WebViewClient {
    protected Context ctx;
    protected MainActivity activity;

    public XCardWebViewClient(Context ctx, MainActivity activity) {
        this.ctx = ctx;
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri uri = request.getUrl();

        // If the target URL has no host and no scheme, return early.
        if (uri.getHost() == null && uri.getScheme() == null) {
            return false; // false : allow to load on webview
        }

        String url = uri.toString();
        if(url.startsWith("file://")) return false;
        if(url.startsWith("http://netstorm.woobi.co.kr/")  || url.startsWith("http://x-card-deeb7.web.app/")  || url.startsWith("http://x-card-deeb7.web.app/")  || url.startsWith("http://hjow.duckdns.org")) return false;
        if(url.startsWith("https://netstorm.woobi.co.kr/") || url.startsWith("https://x-card-deeb7.web.app/") || url.startsWith("https://x-card-deeb7.web.app/") || url.startsWith("https://hjow.duckdns.org")) return false;

        // Handle custom URL schemes such as market:// by attempting to
        // launch the corresponding application in a new intent.
        if (!request.getUrl().getScheme().equals("http")
                && !request.getUrl().getScheme().equals("https")) {
            // If the URL cannot be opened, return early.
            try {
                activity.openURL(uri.toString());
            } catch (ActivityNotFoundException exception) {
                Log.d("TAG", "Failed to load URL with scheme:" + request.getUrl().getScheme());
            }
            return true;
        }

        String currentDomain;
        // If the current URL's host cannot be found, return early.
        try {
            currentDomain = new URI(view.getUrl()).toURL().getHost();
        } catch (URISyntaxException | MalformedURLException exception) {
            // Malformed URL.
            return false;
        }
        String targetDomain = request.getUrl().getHost();

        // If the current domain equals the target domain, the
        // assumption is the user is not navigating away from
        // the site. Reload the URL within the existing web view.
        if (currentDomain.equals(targetDomain)) {
            return false;
        }

        // 3. User is navigating away from the site, open the URL in
        // Custom Tabs to preserve the state of the web view.
        CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
        intent.launchUrl(ctx, request.getUrl());
        return true;
    }
}
