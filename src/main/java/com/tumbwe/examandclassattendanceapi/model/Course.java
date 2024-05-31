package com.tumbwe.examandclassattendanceapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    private String courseCode;

    private String courseName;
}
