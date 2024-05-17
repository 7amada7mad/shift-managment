package com.shiftManager.assigment.shift.shiftBuilder;

import com.shiftManager.assigment.shift.Shift;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftBuilder implements IShiftBuilder{
    private Long shiftId = null;
    private LocalDate shiftDate;

    private LocalTime startTime;
    private LocalTime endTime;

    private boolean recurring;





    @Override
    public IShiftBuilder setDate(LocalDate date) {
        this.shiftDate = date;
        return this;
    }



    @Override
    public IShiftBuilder setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    @Override
    public IShiftBuilder setEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }




    @Override
    public IShiftBuilder setRecurring(boolean recurring) {
        this.recurring = recurring;
        return  this;
    }

    @Override
    public Shift build() {
        return new Shift(shiftId, shiftDate,startTime, endTime, recurring);
    }
}
