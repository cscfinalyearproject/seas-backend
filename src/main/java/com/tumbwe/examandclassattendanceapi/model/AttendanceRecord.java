package com.tumbwe.examandclassattendanceapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID attendanceId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_code")
    private Course course;
    private LocalDate timeStamp;
    @Enumerated(value = EnumType.STRING)
    private AttendanceType attendanceType;


}
