package org.duckdns.hjow.xcard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.addJavascriptInterface(new XCardInterface(this.getApplicationContext(), this), "XCardInterface");
        webView.setNetworkAvailable(true);
        webView.setWebViewClient(new XCardWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setHorizontalScrollBarEnabled(false); //가로 스크롤
        webView.setVerticalScrollBarEnabled(false);   //세로 스크롤
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);

        webView.loadUrl("file:///android_asset/index.html");
    }

    public void openURL(String url) {
        if(! (url.startsWith("http://") || url.startsWith("https://"))) url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        this.startActivity(browserIntent);
    }
}