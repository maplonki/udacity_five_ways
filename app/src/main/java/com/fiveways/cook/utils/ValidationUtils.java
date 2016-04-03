package com.fiveways.cook.utils;

import android.widget.TextView;

/**
 * Created by hugo on 4/2/16.
 */
public class ValidationUtils {
    public static boolean emptyFields(TextView field) {
        return field.getText() == null || field.getText().toString().equals("");
    }
}
