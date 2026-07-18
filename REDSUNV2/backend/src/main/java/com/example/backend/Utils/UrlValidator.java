package com.example.backend.Utils;

import java.util.regex.Matcher;

/**
 * Custom validation utility for processing text patterns.
 */
public class UrlValidator {

    // A robust, standard HTTP/HTTPS/FTP URL matching regex
    private static final String URL_REGEX = 
        "^(https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$";

    /**
     * Checks if a string conforms to a valid web URL scheme.
     */
    public static boolean isValidUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        // Inline reference prevents import collision with Hibernate or Jakarta!
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }
}