package com.tumbwe.examandclassattendanceapi.service;

import com.tumbwe.examandclassattendanceapi.model.AttendanceSessionInOut;
import com.tumbwe.examandclassattendanceapi.model.AttendanceType;

public interface AttendanceSessionService {
    AttendanceSessionInOut startSession(String courseCode, AttendanceType attendanceType);
}
