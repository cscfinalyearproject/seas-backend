package com.tumbwe.examandclassattendanceapi.service.Impl;

import com.tumbwe.examandclassattendanceapi.dto.StudentDto;
import com.tumbwe.examandclassattendanceapi.exception.InternalServerException;
import com.tumbwe.examandclassattendanceapi.exception.ResourceNotFoundException;
import com.tumbwe.examandclassattendanceapi.model.Student;
import com.tumbwe.examandclassattendanceapi.repository.StudentRepository;
import com.tumbwe.examandclassattendanceapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        if (!studentRepository.existsById(studentDto.getStudentId()))
        {
            Student student = new Student(studentDto.getStudentId(), studentDto.getFingerprintTemplate(), studentDto.getFullname());
                try {
                    Student savedStudent = studentRepository.save(student);
                    return new StudentDto(savedStudent.getStudentId(), savedStudent.getFingerprintTemplate(),savedStudent.getFullName());
                }
                catch (Exception e){
                    throw new InternalServerException("Error saving student: " + e.getMessage());
                }
        }
        else throw new DataIntegrityViolationException("Student with id: " + studentDto.getStudentId() + " already exists");
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty())
            throw new ResourceNotFoundException("No Students exist in the database");
        return students;
    }
}
