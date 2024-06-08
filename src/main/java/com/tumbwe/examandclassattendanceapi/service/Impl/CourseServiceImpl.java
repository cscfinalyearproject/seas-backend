package com.tumbwe.examandclassattendanceapi.service.Impl;

import com.tumbwe.examandclassattendanceapi.dto.CourseDto;
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
    public EnrollmentResponse addStudentToCourse(String studentId, String courseCode) {
        Course course = courseRepository.findByCourseCode(courseCode).orElseThrow(()
                -> new ResourceNotFoundException("Course with course code: " + courseCode + " not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student with id: "+ studentId + " not found"));
        course.getEnrolledStudents().add(student);
        try {
            Course savedCourse = courseRepository.save(course);
            String message = "student added successfully to course";
            return new EnrollmentResponse(student.getStudentId(), savedCourse.getCourseCode(), message);
        }
        catch (Exception e){
            throw new InternalServerException("Error adding student to course");
        }


    }
}
