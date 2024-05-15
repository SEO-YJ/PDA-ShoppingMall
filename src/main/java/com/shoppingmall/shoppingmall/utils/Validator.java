package com.shoppingmall.shoppingmall.utils;

import java.util.regex.Pattern;

// TODO: 24.05.14까지 최신화
public class Validator {

    public static boolean isNumber(int num) {
        return Pattern.matches("^[0-9]*$", Integer.toString(num));
    }

    public static boolean isAlpha(String str) {
        return Pattern.matches("^[a-zA-Z]*$", str);
    }
}
