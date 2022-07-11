package com.iverno.gus.commons.general.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	static String DATE_PATTERN = "MM/dd/yyyy";
	public static String  accountNumGenerate() {
		return  getNumericString(20);
		
	}
	
	public static String  dateToString(Date date) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
		String dateString = simpleDateFormat.format(date);
		
		return dateString;
		
		
	}
	public static Object linkedHashMapToObject(LinkedHashMap<String, Object>  linkedHashMap, Class objectclass){
        return new ObjectMapper().convertValue(linkedHashMap, objectclass);
    }
	// function to generate a random string of length n
    static String getNumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "0123456789";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
    
    public static boolean isDouble(String str) {
    	try {
    		double num = Double.parseDouble(str);
    		return true;
			
		} catch (NumberFormatException nE) {
			return false;
		}
   
	}

}
