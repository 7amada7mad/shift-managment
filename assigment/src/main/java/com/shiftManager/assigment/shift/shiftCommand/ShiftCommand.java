package com.shiftManager.assigment.shift.shiftCommand;

import com.shiftManager.assigment.shift.Shift;

import java.util.List;

public interface ShiftCommand {
    List<Shift> execute();
}
