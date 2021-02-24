package com.example.SpringDatabase.service.impl;

import com.example.SpringDatabase.dto.DepartmentRequestDto;
import com.example.SpringDatabase.dto.DepartmentResponseDto;
import com.example.SpringDatabase.entity.Department;
import com.example.SpringDatabase.repository.DepartmentRepository;
import com.example.SpringDatabase.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();

        BeanUtils.copyProperties(departmentRequestDto, department);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment, departmentResponseDto);

        return departmentResponseDto;
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }
}
