package org.duckdns.hjow.xcard;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class XCardWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}
