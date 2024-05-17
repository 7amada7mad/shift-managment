package com.shiftManager.assigment.shift.daysOfWeekFactory;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class DaysOfWeekArrayListFactory{
    public ArrayList<DayOfWeek> extractDaysOfWeek(String daysInput){
        String str = daysInput;
        str = str.replaceAll("[\\[\\]\\s]", "");
        String[] days = str.split(",");
        ArrayList<DayOfWeek> newArray = new ArrayList<>();
        for (String day : days){
            newArray.add(DayOfWeek.valueOf(day));
        }

        return newArray;
    }

}
