package com.shiftManager.assigment.shift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("shifts")
public class ShiftController {
    @Autowired
    ShiftService shiftService;
    @PostMapping
    private ResponseEntity<Void> createShift(@RequestBody Map<String, String> shiftDetails){
        return shiftService.createShift(shiftDetails);
    }
    @GetMapping(path = "{sortingCondition}")
    private ResponseEntity<List<Shift>> getAllShifts(@PathVariable String sortingCondition){
        return shiftService.getAllShifts(sortingCondition);
    }
}
