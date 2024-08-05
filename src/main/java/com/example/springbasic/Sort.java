package com.example.springbasic;

import java.util.List;

public class Sort {

    public List<String> sortByLength(List<String> list) {
        list.sort((str1, str2) -> {
            if (str1.length() == str2.length()) return str1.charAt(0) - str2.charAt(0);
            return str1.length() - str2.length();
        });

        return list;
    }

}
