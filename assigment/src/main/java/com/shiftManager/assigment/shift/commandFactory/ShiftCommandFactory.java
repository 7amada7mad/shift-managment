package com.shiftManager.assigment.shift.commandFactory;

import com.shiftManager.assigment.shift.shiftCommand.*;

import java.util.Map;

public class ShiftCommandFactory {
    public static ShiftCommand getCommand(Map<String, String> details) {
        String shiftType = details.get("shiftType");

        return switch (shiftType) {
            case "oneSingleShift" -> new CreateOneSingleShiftCommand(details);
            case "recurringMultipleShifts" -> new CreateMultipleRecurringShiftsCommand(details);
            default -> new SendNoShiftIsCreatedCommand();
        };
    }
}
