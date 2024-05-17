package com.shiftManager.assigment.shift.shiftSorting;

import com.shiftManager.assigment.shift.Shift;
import java.util.ArrayList;


public class ReturnShiftListWithSortingRequirements {

    public static  ArrayList<Shift> handleShiftList(ArrayList<Shift> shiftList, String sortingRequired) {


        return switch (sortingRequired) {
            case "sorted" -> sortIncomingList(shiftList);
            case "unsorted" -> shiftList;
            default -> throw new IllegalArgumentException("Invalid sorting requirement: " + sortingRequired);
        };
    }
    public static ArrayList<Shift> sortIncomingList(ArrayList<Shift> shiftList){
        MergeSortShifts.mergeSort(shiftList);
        return shiftList;
    }
}
