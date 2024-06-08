package com.tumbwe.examandclassattendanceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {
    private String studentId;
    private byte[] fingerprintTemplate;
}
