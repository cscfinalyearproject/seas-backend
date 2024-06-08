package com.tumbwe.examandclassattendanceapi.service;

import com.tumbwe.examandclassattendanceapi.dto.CourseDto;
import com.tumbwe.examandclassattendanceapi.dto.EnrollmentResponse;

public interface CourseService {
    CourseDto addCourse(CourseDto courseDto);

    EnrollmentResponse addStudentToCourse(String studentId, String courseCode);
}
