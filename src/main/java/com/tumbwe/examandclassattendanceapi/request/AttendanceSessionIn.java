package com.tumbwe.examandclassattendanceapi.request;

import com.tumbwe.examandclassattendanceapi.model.AttendanceType;
import com.tumbwe.examandclassattendanceapi.model.Student;
import lombok.Data;

import java.util.Set;

@Data
public class AttendanceSessionIn {

    private AttendanceType attendanceType;
    private String courseCode;
    private Set<Student> presentStudents;
}
