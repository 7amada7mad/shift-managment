package com.shiftManager.assigment.shift.shiftBuilder;

import com.shiftManager.assigment.shift.Shift;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IShiftBuilder {
    IShiftBuilder setDate(LocalDate shiftDate);
    IShiftBuilder setStartTime(LocalTime startTime);
    IShiftBuilder setEndTime(LocalTime endTime);

    IShiftBuilder setRecurring(boolean recurring);
    Shift build();
}
