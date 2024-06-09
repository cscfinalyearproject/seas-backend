package com.tumbwe.examandclassattendanceapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "attendance_sessions")
public class AttendanceSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID attendanceSessionId;
    @ManyToOne
    @JoinColumn(name = "course_code")
    private Course course;
    @Enumerated(EnumType.STRING)
    private AttendanceType attendanceType;
    private LocalDate timeStamp;

    public AttendanceSession(Course course, AttendanceType attendanceType){
        this.course = course;
        this.attendanceType = attendanceType;
        this.timeStamp = LocalDate.now();
    }
}
