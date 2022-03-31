package com.example.browser;

import android.content.Context;
import android.webkit.WebView;

public class SearchBarHelper {
    WebViewManager webViewManager;

    public SearchBarHelper(WebViewManager webViewManager) {
        this.webViewManager = webViewManager;
    }

    public void submitSearch(String partialQuery) {
        if (isSpecialQuery(partialQuery)) {
            handleSpecialQuery(partialQuery);
        } else {
            String query = buildRealQuery(partialQuery);
            webViewManager.display(query, true);
        }
    }

    private boolean isSpecialQuery(String query) {
        return query.equals("index.html");
    }

    private void handleSpecialQuery(String query) {
        switch (query) {
            case "index.html":
                webViewManager.display("file:///android_asset/index.html", true);
                break;
        }
    }

    private String buildRealQuery(String partialQuery) {
        return "https://" + partialQuery;
    }
}
