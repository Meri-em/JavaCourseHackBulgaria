package com.hackbulgaria.corejava.tddintro;

import java.util.ArrayList;
import java.util.Collections;


public class TDD {
	public static int getNumberOfDigits(int number){
	   int counter = 0;
	   if(number == 0) {
	       counter = 1;
	   }
	   while(number != 0){
	       number /= 10;
	       counter++;
	   }
	   return counter;
	}
	
	public static String connectObjects(String delimiter, Object ... arguments){
	    StringBuilder result = new StringBuilder();
	    for(int i = 0; i < arguments.length - 1; i++){
	        result.append(arguments[i] + delimiter);
	    }
	    result.append(arguments[arguments.length - 1]);
	    return result.toString(); 
	}
	
	public static String reduceFilePath(String path){
	    String[] arr = path.split("/+");
	    ArrayList<String> directories = new ArrayList<String>();
	    int i = arr.length - 1;
	    while(i > 0 ){
            if(arr[i].equals("..")){
                i-=2;
            }
            else if(arr[i].equals(".") || arr[i].equals("")){
                i-=1;
            }
            else {
                directories.add(arr[i]);
                i -=1;
            }
        }
       if(directories.isEmpty())
          return "/";
      
       Collections.reverse(directories);
       Object[] result = new Object[directories.size()];
       for(int j = 0; j < result.length; j++){
           result[j] = directories.get(j).toString();
       }
	   
	   return "/" + connectObjects("/", result)  ;
	    
	}

    
}
