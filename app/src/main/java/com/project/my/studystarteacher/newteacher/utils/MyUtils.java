package com.project.my.studystarteacher.newteacher.utils;

import android.text.TextUtils;
import android.widget.EditText;

public class MyUtils {
    public static boolean isNull(EditText s, String msg) {
        if (TextUtils.isEmpty(s.getText().toString().trim())) {
            s.setError(msg);
            s.requestFocus();
            return true;
        }
        return false;

    }
}
