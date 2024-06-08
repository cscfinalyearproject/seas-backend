package com.tumbwe.examandclassattendanceapi.service.Impl;

import com.tumbwe.examandclassattendanceapi.dto.StudentDto;
import com.tumbwe.examandclassattendanceapi.exception.InternalServerException;
import com.tumbwe.examandclassattendanceapi.model.Student;
import com.tumbwe.examandclassattendanceapi.repository.StudentRepository;
import com.tumbwe.examandclassattendanceapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        if (!studentRepository.existsById(studentDto.getStudentId()))
        {
            Student student = new Student(studentDto.getStudentId(), studentDto.getFingerprintTemplate());
                try {
                    Student savedStudent = studentRepository.save(student);
                    return new StudentDto(savedStudent.getStudentId(), studentDto.getFingerprintTemplate());
                }
                catch (Exception e){
                    throw new InternalServerException("Error saving student: " + e.getMessage());
                }
        }
        else throw new DataIntegrityViolationException("Student with id: " + studentDto.getStudentId() + " already exists");
    }
}
