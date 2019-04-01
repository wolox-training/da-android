package ar.com.wolox.android.example.utils;

import java.util.regex.Pattern;

/**
 * File to store constants
 */
public final class Constants {

    private Constants() {
    }

    public static final String WOLOX_WEB = "http://www.wolox.com.ar";
    public static final int SPLASH_TIME = 1000;
    public static final Pattern EMAIL_PATTERN = Pattern.
            compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
            );

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
}