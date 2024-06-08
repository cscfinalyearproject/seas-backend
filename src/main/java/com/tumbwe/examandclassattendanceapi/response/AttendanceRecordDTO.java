package com.tumbwe.examandclassattendanceapi.response;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceRecordDTO {
        private String studentId;
        private String courseCode;
        private LocalDateTime timestamp;
        private String attendanceType;

        public AttendanceRecordDTO(String studentId, String courseCode, String attendanceType) {
            this.studentId = studentId;
            this.courseCode = courseCode;
            this.timestamp = LocalDateTime.now();
            this.attendanceType = attendanceType;
        }

        // Getters and setters
    }


