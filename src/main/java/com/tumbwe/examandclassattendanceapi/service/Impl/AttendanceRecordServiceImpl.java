package com.tumbwe.examandclassattendanceapi.service.Impl;

import com.tumbwe.examandclassattendanceapi.exception.InternalServerException;
import com.tumbwe.examandclassattendanceapi.exception.ResourceNotFoundException;
import com.tumbwe.examandclassattendanceapi.model.*;
import com.tumbwe.examandclassattendanceapi.repository.AttendanceRecordRepository;
import com.tumbwe.examandclassattendanceapi.repository.AttendanceSessionRepository;
import com.tumbwe.examandclassattendanceapi.repository.StudentRepository;
import com.tumbwe.examandclassattendanceapi.response.AttendanceRecordDTO;
import com.tumbwe.examandclassattendanceapi.service.AttendanceRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final StudentRepository studentRepository;
    private final AttendanceSessionRepository attendanceSessionRepository;
    @Override
    public Set<AttendanceRecordDTO> addAttendanceRecord(String courseCode, AttendanceType attendanceType, Set<byte[]> studentFingerprintsSet) {
        LocalDate date = LocalDate.now();
        AttendanceSession attendanceSession = attendanceSessionRepository.findByCourseCode(courseCode,attendanceType, date)
                                        .orElseThrow(() -> new ResourceNotFoundException("Attendance session not found"));

        Set<Student> presentStudents = studentFingerprintsSet
                .stream()
                .map(studentRepository::findByFingerprintTemplate)
                .peek(student -> {
                    if (student == null)
                        throw new InternalServerException("Error retrieving student");
                    else {
                        log.info("student fingerTemplate {}", student.getFingerprintTemplate());
                    }

                })
                .collect(Collectors.toSet());

        Set<AttendanceRecord> records = presentStudents.stream()
                              .map(student -> {
            return new AttendanceRecord(student, attendanceSession.getCourse(), attendanceType);
                              }
        ).map(attendanceRecordRepository::save).collect(Collectors.toSet());
        records.forEach(record -> log.info("record: {}", record.getStudent().getStudentId()));

        return records.stream()
                .map(record -> new AttendanceRecordDTO(
                        record.getStudent().getStudentId(),
                        record.getCourse().getCourseCode(),// Assuming you have a timestamp field in AttendanceRecord
                        record.getAttendanceType()
                ))
                .collect(Collectors.toSet());

    }
}
