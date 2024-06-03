package com.tumbwe.examandclassattendanceapi.controller;

import com.tumbwe.examandclassattendanceapi.exception.ResourceNotFoundException;
import com.tumbwe.examandclassattendanceapi.model.AttendanceRecord;
import com.tumbwe.examandclassattendanceapi.model.AttendanceSessionInOut;
import com.tumbwe.examandclassattendanceapi.model.AttendanceType;
import com.tumbwe.examandclassattendanceapi.model.Student;
import com.tumbwe.examandclassattendanceapi.service.AttendanceRecordService;
import com.tumbwe.examandclassattendanceapi.service.AttendanceSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceRecordController {

    private final AttendanceSessionService attendanceSessionService;
    private final AttendanceRecordService attendanceRecordService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/start")
    public ResponseEntity<?> startAttendanceTaking(@RequestParam String courseCode, @RequestParam AttendanceType attendanceType){
        try {
            AttendanceSessionInOut response = attendanceSessionService.startSession(courseCode, attendanceType);
            return ResponseEntity.ok(response);
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
         catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Saving session to database");
         }
    }

    @PostMapping("/mark-attendance")
    public ResponseEntity<?> markAttendance(@RequestBody AttendanceSessionInOut attendanceSessionIn){
        Set<byte[]> studentFingerprintsSet = attendanceSessionIn.getStudentPrints();
        if (!studentFingerprintsSet.isEmpty()){
         Set<AttendanceRecord> attendanceRecords = attendanceRecordService.addAttendanceRecord(attendanceSessionIn.getCourseCode(),
                                                                                attendanceSessionIn.getAttendanceType(),
                                                                                studentFingerprintsSet);
         return ResponseEntity.ok(attendanceRecords);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Students absent");
        }
    }
}
