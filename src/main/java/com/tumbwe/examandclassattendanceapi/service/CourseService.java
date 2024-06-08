package com.tumbwe.examandclassattendanceapi.service;

import com.tumbwe.examandclassattendanceapi.dto.CourseDto;
import com.tumbwe.examandclassattendanceapi.dto.EnrollmentDto;
import com.tumbwe.examandclassattendanceapi.dto.EnrollmentResponse;

import java.util.List;

public interface CourseService {
    CourseDto addCourse(CourseDto courseDto);



    List<CourseDto> getAllCourses();

    EnrollmentResponse addStudentToCourse(EnrollmentDto enrollmentDto);
}
