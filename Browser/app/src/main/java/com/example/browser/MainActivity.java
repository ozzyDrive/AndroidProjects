package com.example.browser;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SearchBarHelper searchBarHelper;
    WebViewManager webViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchBar = (EditText) findViewById(R.id.searchBar);
        WebView webView = (WebView) findViewById(R.id.webView);

        webViewManager = new WebViewManager(webView);
        searchBarHelper = new SearchBarHelper(webViewManager);
        searchBar.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchBarHelper.submitSearch(searchBar.getText().toString());
                return true;
            }
            return false;
        });
    }

    public void handleRefreshClick(View view) {
        webViewManager.refresh();
    }

    public void handleShoutClick(View view) {
        if (webViewManager.getCurrentPage().equals("file:///android_asset/index.html")) {
            webViewManager.evaluateJavascript("javascript:shoutOut();");
        }
    }

    public void handleInitializeClick(View view) {
        if (webViewManager.getCurrentPage().equals("file:///android_asset/index.html")) {
            webViewManager.evaluateJavascript("javascript:initialize();");
        }
    }

    public void handleNextPageClick(View view) {
        webViewManager.moveNextPage();
    }

    public void handlePreviousPageClick(View view) {
        webViewManager.movePreviousPage();
    }
}