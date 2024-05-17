package com.shiftManager.assigment.shift;

import com.shiftManager.assigment.shift.commandFactory.ShiftCommandFactory;
import com.shiftManager.assigment.shift.shiftCommand.ShiftCommand;
import com.shiftManager.assigment.shift.shiftSorting.ReturnShiftListWithSortingRequirements;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.shiftManager.assigment.shift.shiftSorting.ReturnShiftListWithSortingRequirements.handleShiftList;

@Service
@AllArgsConstructor
public class ShiftService {

    ShiftRepository shiftRepository;
    public ResponseEntity<Void> createShift(Map<String, String> shiftDetails) {
        ShiftCommand shiftCommand = ShiftCommandFactory.getCommand(shiftDetails);
        List<Shift> shiftList = shiftCommand.execute();
        if (shiftList==null){
            return ResponseEntity.badRequest().build();
        }

        List<Shift> shiftsAdded = shiftRepository.saveAll(shiftList);

        if (!shiftsAdded.isEmpty()){

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    public ResponseEntity<List<Shift>> getAllShifts(String sortingRequired) {
        ArrayList<Shift> shiftsInDB = new ArrayList<>(shiftRepository.findAll());
        if (shiftsInDB.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        ArrayList<Shift> shiftsToSendBack = handleShiftList(shiftsInDB, sortingRequired);
        return ResponseEntity.ok(shiftsToSendBack);
    }
}
