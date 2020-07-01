package com.example.tablayout.widgets;

public class ConvertHelper {

    public static String numberToStringID(int number){
        return numberToStringWithMaxLength(number, 4);
    }

    public static String numberToStringWithMaxLength(int number, int maxLength){
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(number));
        int builderLength = stringBuilder.length();
        for(int i=0; i < maxLength-builderLength; i++){
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();
    }
}
