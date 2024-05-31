package com.tumbwe.examandclassattendanceapi.controller;

import com.tumbwe.examandclassattendanceapi.model.AttendanceType;
import com.tumbwe.examandclassattendanceapi.service.AttendanceSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceRecordController {

    private final AttendanceSessionService attendanceSessionService;

    @PostMapping("/start")
    public ResponseEntity<?> startAttendanceTaking(@RequestParam String courseCode,@RequestParam AttendanceType attendanceType){
        try {

        }
  catch (Exception e){}
    }
}
