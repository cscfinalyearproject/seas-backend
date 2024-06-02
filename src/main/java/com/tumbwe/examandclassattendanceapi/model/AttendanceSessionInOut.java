package com.tumbwe.examandclassattendanceapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class AttendanceSessionInOut {
    private String courseCode;
    private AttendanceType attendanceType;
    private Set<byte[]> studentPrints;
    private LocalDate timeStamp;

    public AttendanceSessionInOut(String courseCode, AttendanceType attendanceType, Set<byte[]> studentPrints) {
        this.courseCode = courseCode;
        this.attendanceType = attendanceType;
        this.studentPrints = studentPrints;
        this.timeStamp = LocalDate.now();
    }
}
