package com.pdf.export.util;

public class StringUtil {

    private StringUtil() {
        super();
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.length() == 0;
    }
}
