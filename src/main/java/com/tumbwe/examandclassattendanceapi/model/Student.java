package com.tumbwe.examandclassattendanceapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Students")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String studentId;
    private byte[] fingerprintTemplate;
    private String fullName;

}
