package com.tumbwe.examandclassattendanceapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSession {
    private String courseCode;
    private AttendanceType attendanceType;
    private Set<Student> enrolledStudents;
    private LocalDate timeStamp;

}
