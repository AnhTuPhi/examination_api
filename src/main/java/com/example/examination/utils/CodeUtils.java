package com.example.examination.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeUtils {
    private static final Logger logger = LoggerFactory.getLogger(CodeUtils.class);
    public static String getRandomCode8Digit(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        int n = 8;
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
