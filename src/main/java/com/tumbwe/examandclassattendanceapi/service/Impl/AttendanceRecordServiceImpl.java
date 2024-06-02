package com.tumbwe.examandclassattendanceapi.service.Impl;

import com.tumbwe.examandclassattendanceapi.exception.InternalServerException;
import com.tumbwe.examandclassattendanceapi.exception.ResourceNotFoundException;
import com.tumbwe.examandclassattendanceapi.model.AttendanceRecord;
import com.tumbwe.examandclassattendanceapi.model.AttendanceType;
import com.tumbwe.examandclassattendanceapi.model.Course;
import com.tumbwe.examandclassattendanceapi.model.Student;
import com.tumbwe.examandclassattendanceapi.repository.AttendanceRecordRepository;
import com.tumbwe.examandclassattendanceapi.repository.CourseRepository;
import com.tumbwe.examandclassattendanceapi.repository.StudentRepository;
import com.tumbwe.examandclassattendanceapi.service.AttendanceRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    @Override
    public Set<AttendanceRecord> addAttendanceRecord(String courseCode, AttendanceType attendanceType, Set<byte[]> studentFingerprintsSet) {
        Course course = courseRepository.findByCourseCode(courseCode)
                                        .orElseThrow(() -> new InternalServerException("Error retrieving course"));

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
            return new AttendanceRecord(student, course, attendanceType);
                              }
        ).map(attendanceRecordRepository::save).collect(Collectors.toSet());
        records.forEach(record -> log.info("record: {}", record.getStudent().getStudentId()));
        return records;
    }
}
