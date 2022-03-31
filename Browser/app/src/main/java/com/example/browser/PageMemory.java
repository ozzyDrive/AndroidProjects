package com.example.browser;

import java.util.ArrayList;

public class PageMemory {
    ArrayList<String> urlArray;
    int cursor;

    public PageMemory() {
        urlArray = new ArrayList<String>();
        cursor = 0;
    }

    public void addPage(String url) {
        removeTrailingPages();
        urlArray.add(url);
        cursor = urlArray.size() - 1;
    }

    public String getCurrentPage() {
        return urlArray.get(cursor);
    }

    public String moveNext() {
        if (!isCursorAtEnd()) {
            cursor += 1;
        }
        return getCurrentPage();
    }

    public String movePrevious() {
        if (!isCursorAtStart()) {
            cursor -= 1;
        }
        return getCurrentPage();
    }

    public boolean isCursorAtEnd() {
        return cursor == urlArray.size() - 1;
    }

    public boolean isCursorAtStart() {
        return cursor == 0;
    }

    private void removeTrailingPages() {
        if (!isCursorAtEnd()) {
            for (int index = urlArray.size() - 1; index > cursor; index--) {
                urlArray.remove(index);
            }
        }
    }
}
