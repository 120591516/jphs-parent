package com.jinpaihushi.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * 自动生成ID
     */
    public static String getId() {

        return UUID.randomUUID().toString().replace("-", "");

    }

    private static int getRandom(int count) {
    	  return (int) Math.round(Math.random() * (count));
    }
   
    private static String string = "abcdefghijklmnopqrstuvwxyz";   
    
	public static String getRandomString(int length){
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt(getRandom(len-1)));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
    	
		System.out.println(getRandomString(8));
	}
    
}

