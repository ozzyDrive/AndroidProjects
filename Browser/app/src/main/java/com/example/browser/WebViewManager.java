package com.example.browser;

import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewManager {
    WebView webView;
    PageMemory pageMemory;

    public WebViewManager(WebView webView) {
        this.webView = webView;
        this.pageMemory = new PageMemory();
        setupWebView();
    }

    public void display(String url, boolean addToMemory) {
        if (addToMemory) {
            pageMemory.addPage(url);
        }
        webView.loadUrl(url);
    }

    public void refresh() {
        webView.reload();
    }

    public String getCurrentPage() {
        String url = webView.getOriginalUrl();
        return url != null ? url : "";
    }

    public void evaluateJavascript(String expression) {
        webView.evaluateJavascript(expression, null);
    }

    public void moveNextPage() {
        String url = pageMemory.moveNext();
        if (!url.equals(getCurrentPage())) {
            display(url, false);
        }
    }

    public void movePreviousPage() {
        String url = pageMemory.movePrevious();
        if (!url.equals(getCurrentPage())) {
            display(url, false);
        }
    }

    private void setupWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!url.equals(pageMemory.getCurrentPage())) {
                    pageMemory.addPage(url);
                }
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
    }
}
