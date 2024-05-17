package com.shiftManager.assigment.shift.shiftCommand;

import com.shiftManager.assigment.shift.Shift;
import com.shiftManager.assigment.shift.shiftBuilder.ShiftBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateOneSingleShiftCommand implements ShiftCommand{
    private List<Shift> shifts;
    private LocalDate shiftDate;
    private LocalTime startTime;
    private LocalTime endTime;


    public CreateOneSingleShiftCommand(Map<String, String> details){
        this.shiftDate = LocalDate.parse(details.get("shiftDate"));
        this.startTime = LocalTime.parse(details.get("startTime"));
        this.endTime = LocalTime.parse(details.get("endTime"));

        this.shifts = new ArrayList<>();
    }

    @Override
    public List<Shift> execute() {
        Shift shift = new ShiftBuilder()
                .setDate(shiftDate)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .build();
        shifts.add(shift);
        return shifts;
    }
}
