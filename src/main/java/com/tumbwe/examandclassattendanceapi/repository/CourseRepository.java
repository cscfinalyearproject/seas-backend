package com.tumbwe.examandclassattendanceapi.repository;

import com.tumbwe.examandclassattendanceapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
