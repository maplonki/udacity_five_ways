package com.fiveways.cook.utils;

import android.widget.TextView;

/**
 * Created by hugo on 4/2/16.
 * <p/>
 * This is a utility class that provides
 * convenience methods to validate user data.
 */
public class ValidationUtils {

    /**
     * This method validates if TextView has any text
     *
     * @param field the TextView to be validated
     * @return true if the field has no text, false otherwise
     */
    public static boolean emptyFields(TextView field) {
        return field.getText() == null || field.getText().toString().equals("");
    }
}
