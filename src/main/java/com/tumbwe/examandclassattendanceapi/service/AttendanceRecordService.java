package com.tumbwe.examandclassattendanceapi.service;

import com.tumbwe.examandclassattendanceapi.model.AttendanceType;
import com.tumbwe.examandclassattendanceapi.response.AttendanceRecordDTO;

import java.util.Set;

public interface AttendanceRecordService {

    Set<AttendanceRecordDTO> addAttendanceRecord(String courseCode, AttendanceType attendanceType, Set<byte[]> studentFingerprintsSet);
}
