package com.progressSoft.taskProgressSoft.config;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyResourceBundle {

    public static String getString(String key, Locale locale) {
        if (ResourceBundle.getBundle("messages", locale).containsKey(key)) {
            String value = ResourceBundle.getBundle("messages", locale).getString(key);
            try {
                return new String(value.getBytes("ISO-8859-1"), "UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                return value;
            }
        }
        else {
            return key;
        }
    }

}
