package com.tumbwe.examandclassattendanceapi.service;

import com.tumbwe.examandclassattendanceapi.model.AttendanceRecord;
import com.tumbwe.examandclassattendanceapi.model.AttendanceType;

import java.util.Set;

public interface AttendanceRecordService {

    Set<AttendanceRecord> addAttendanceRecord(String courseCode, AttendanceType attendanceType, Set<byte[]> studentFingerprintsSet);
}
