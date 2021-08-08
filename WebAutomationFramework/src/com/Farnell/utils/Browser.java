package com.Farnell.utils;

public enum Browser {
    InternetExplorer("IE"),
    Chrome("CHROME"),
    Firefox("FIREFOX");

    String browserName;
    Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

}
