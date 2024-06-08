package com.tumbwe.examandclassattendanceapi.service.Impl;

import com.tumbwe.examandclassattendanceapi.dto.CourseDto;
import com.tumbwe.examandclassattendanceapi.dto.EnrollmentDto;
import com.tumbwe.examandclassattendanceapi.dto.EnrollmentResponse;
import com.tumbwe.examandclassattendanceapi.exception.InternalServerException;
import com.tumbwe.examandclassattendanceapi.exception.ResourceNotFoundException;
import com.tumbwe.examandclassattendanceapi.model.Course;
import com.tumbwe.examandclassattendanceapi.model.Student;
import com.tumbwe.examandclassattendanceapi.repository.CourseRepository;
import com.tumbwe.examandclassattendanceapi.repository.StudentRepository;
import com.tumbwe.examandclassattendanceapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        if(!courseRepository.existsById(courseDto.getCourseCode())) {
            Course course = new Course();
            course.setCourseCode(courseDto.getCourseCode());
            course.setCourseName(courseDto.getCourseName());
            try {
                Course savedCourse = courseRepository.save(course);
                return new CourseDto(savedCourse.getCourseName(), courseDto.getCourseCode());
            }
            catch (Exception e){
                throw new InternalServerException(e.getMessage());
            }
        }
        else {
            throw new DataIntegrityViolationException("Course with course code: " + courseDto.getCourseCode()
                    +" already exists");
        }
         }

    @Override
    public EnrollmentResponse addStudentToCourse(EnrollmentDto enrollmentDto) {
        Course course = courseRepository.findByCourseCode(enrollmentDto.getCourseCode()).orElseThrow(()
                -> new ResourceNotFoundException("Course with course code: " + enrollmentDto.getCourseCode() + " not found"));
        Student student = studentRepository.findById(enrollmentDto.getStudentId()).orElseThrow(()->
                new ResourceNotFoundException("Student with id: "+ enrollmentDto.getStudentId() + " not found"));
        if (course.getEnrolledStudents().contains(student))
            throw new DataIntegrityViolationException("student with id :" + enrollmentDto.getStudentId() + " already enrolled in course");
        course.getEnrolledStudents().add(student);
        try {
            Course savedCourse = courseRepository.save(course);
            String message = "student added successfully to course";
            return new EnrollmentResponse(student.getStudentId(), savedCourse.getCourseCode(), message);
        }
        catch (Exception e){
            throw new InternalServerException(e.getMessage());
        }


    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (!courses.isEmpty())
        return courses.stream().map(course ->
                new CourseDto(course.getCourseName(), course.getCourseCode())).collect(Collectors.toList());

        else
            throw new ResourceNotFoundException("Courses not found");
    }


}
