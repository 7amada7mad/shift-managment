package com.shiftManager.assigment.shift.shiftCommand;

import com.shiftManager.assigment.shift.Shift;
import com.shiftManager.assigment.shift.daysOfWeekFactory.DaysOfWeekArrayListFactory;
import com.shiftManager.assigment.shift.shiftBuilder.ShiftBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateMultipleRecurringShiftsCommand implements ShiftCommand{
    private List<Shift> shifts;

    private List<DayOfWeek> daysArray;
    private int numberOfWeeks;
    private int startingWeek;
    private Map<String, String> details;
    public CreateMultipleRecurringShiftsCommand(Map<String, String> details) {

        this.shifts = new ArrayList<>();
        this.details = details;
        this.daysArray = new DaysOfWeekArrayListFactory().extractDaysOfWeek(details.get("daysInput"));
        this.numberOfWeeks = Integer.parseInt(details.get("numberOfWeeks"));
        this.startingWeek = Integer.parseInt(details.get("startingWeek"));
    }
    @Override
    public List<Shift> execute() {
        for (int week = startingWeek; week < numberOfWeeks+startingWeek; week++) {
            LocalDate date = LocalDate.now().withDayOfYear(1).plusWeeks(week-1);//Datumet för först inträffade måndag under den valda start veckan.

            for (DayOfWeek day : daysArray) {
                date = date.with(TemporalAdjusters.nextOrSame(day));
                Shift shift = new ShiftBuilder()
                        .setDate(date)
                        .setStartTime(LocalTime.parse(details.get("startTime")))
                        .setEndTime(LocalTime.parse(details.get("endTime")))
                        .setRecurring(true)
                        .build();

                shifts.add(shift);

            }
        }
        return shifts;
    }
}
