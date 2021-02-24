package com.example.SpringDatabase.service;

import com.example.SpringDatabase.dto.DepartmentRequestDto;
import com.example.SpringDatabase.dto.DepartmentResponseDto;
import com.example.SpringDatabase.entity.Department;

public interface DepartmentService {
    DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);

    Department getDepartmentById(Long id);
}
