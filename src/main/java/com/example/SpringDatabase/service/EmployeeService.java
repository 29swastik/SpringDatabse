package com.example.SpringDatabase.service;

import com.example.SpringDatabase.dto.EmployeeRequestDto;
import com.example.SpringDatabase.dto.EmployeeResponseDto;
import org.springframework.stereotype.Service;


public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployeeById(long id);
    EmployeeResponseDto updateEmployeeById(long id, EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto deleteEmployeeById(Long id);
}
