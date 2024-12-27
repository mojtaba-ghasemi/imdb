package com.imdb.imdb.exception;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ErrorCodeReaderUtil {
    static ResourceBundle rb = ResourceBundle.getBundle("errorcodes");

    public static String getResourceProperity(String key) {
        try {
            return rb.getString(key);
        } catch (Exception e) {
//            return "UNHANDLED_EXCEPTION_KEY : " + key;
            return MessageFormat.format("{0} : {1}", rb.getString("UNHANDLED_EXCEPTION"), key);
        }
    }

    public static Enumeration<String> getResourceKeys(String key) {
        return rb.getKeys();
    }
}
