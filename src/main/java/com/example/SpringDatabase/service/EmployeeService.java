package com.example.SpringDatabase.service;

import com.example.SpringDatabase.dto.EmployeeRequestDto;
import com.example.SpringDatabase.dto.EmployeeResponseDto;

import java.util.List;


public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployeeById(long id);
    EmployeeResponseDto updateEmployeeById(long id, EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto deleteEmployeeById(Long id);

    List<EmployeeResponseDto> getEmployeeListByDepartment(Long id);
}
