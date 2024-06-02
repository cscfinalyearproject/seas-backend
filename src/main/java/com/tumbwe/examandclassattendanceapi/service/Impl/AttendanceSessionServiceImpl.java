package com.tumbwe.examandclassattendanceapi.service.Impl;

import com.tumbwe.examandclassattendanceapi.exception.ResourceNotFoundException;
import com.tumbwe.examandclassattendanceapi.model.*;
import com.tumbwe.examandclassattendanceapi.repository.AttendanceSessionRepository;
import com.tumbwe.examandclassattendanceapi.repository.CourseRepository;
import com.tumbwe.examandclassattendanceapi.service.AttendanceSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceSessionServiceImpl implements AttendanceSessionService {

    private final AttendanceSessionRepository attendanceSessionRepository;
    private final CourseRepository courseRepository;
    @Override
    public AttendanceSessionInOut startSession(String courseCode, AttendanceType attendanceType) {
        Course course = courseRepository.findByCourseCode(courseCode).orElseThrow(() -> new ResourceNotFoundException("Course not found exception"));
        AttendanceSession attendanceSession = new AttendanceSession(course, attendanceType);
        attendanceSessionRepository.save(attendanceSession);

        Set<Student> students = courseRepository.findStudentsByCourseCode(courseCode);
        if (!students.isEmpty()){
            Set<byte[]> studentFingerprintTemplates = students.stream()
                                                              .map(Student::getFingerprintTemplate)
                                                              .collect(Collectors.toSet());
            return new AttendanceSessionInOut(courseCode, attendanceType, studentFingerprintTemplates);
        }
        else {
            throw new ResourceNotFoundException("No Students enrolled to the course");
        }

        }


}

