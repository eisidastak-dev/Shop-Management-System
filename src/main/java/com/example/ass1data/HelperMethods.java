package com.example.ass1data;

public class HelperMethods {


    public static String truncateString(String input, int length){
        if(input != null){
            if(input.length() <= length){
                return input;
            }
            else {
                return input.substring(0, length);
            }
        }
        else return null;
    }









}