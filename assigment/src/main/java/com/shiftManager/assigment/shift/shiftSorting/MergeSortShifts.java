package com.shiftManager.assigment.shift.shiftSorting;

import com.shiftManager.assigment.shift.Shift;

import java.util.ArrayList;

public class MergeSortShifts {
    public static void mergeSort(ArrayList<Shift> shifts) {
        if (shifts.size() <= 1) {
            return;
        }

        int mid = shifts.size() / 2;
        ArrayList<Shift> left = new ArrayList<>(shifts.subList(0, mid));
        ArrayList<Shift> right = new ArrayList<>(shifts.subList(mid, shifts.size()));

        mergeSort(left);
        mergeSort(right);
        merge(shifts, left, right);
    }

    private static void merge(ArrayList<Shift> shifts, ArrayList<Shift> left, ArrayList<Shift> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getShiftDate().isBefore(right.get(j).getShiftDate())) {
                shifts.set(k++, left.get(i++));
            } else {
                shifts.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            shifts.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            shifts.set(k++, right.get(j++));
        }
    }
}
