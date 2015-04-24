package com.corejava.files;
public  class DayEnum{
    
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
   
    public static String greetMe(Day day){
        switch(day){
            case MONDAY:  return "Hello";
            case TUESDAY: return "Hi";
            case THURSDAY: return "Aloha";
            case FRIDAY: return "Holla";
            case SATURDAY: return "Good morning";
            case SUNDAY: return "Good evening";
            default: return "Not a valid day";       
                
        }
    }
}