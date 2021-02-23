package com.example.SpringDatabase.service.impl;

import com.example.SpringDatabase.dto.EmployeeRequestDto;
import com.example.SpringDatabase.dto.EmployeeResponseDto;
import com.example.SpringDatabase.entity.Employee;
import com.example.SpringDatabase.repository.EmployeeRepository;
import com.example.SpringDatabase.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();

        //copy files from dto to employee
        BeanUtils.copyProperties(employeeRequestDto, employee);

        Employee savedEmployee = employeeRepository.save(employee);

        //copy from Employee to responseDto
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(savedEmployee, employeeResponseDto);

        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            //copy from employye to employeeresponsedto
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(), employeeResponseDto);
            return employeeResponseDto;
        }

        return null;
    }

    @Override
    public EmployeeResponseDto updateEmployeeById(long id, EmployeeRequestDto employeeRequestDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            Employee employeeFromDb = employeeOptional.get();
            employeeFromDb.setName(employeeRequestDto.getName());
            employeeFromDb.setDepartmentName(employeeRequestDto.getDepartmentName());

            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(savedEmployee, employeeResponseDto);
            return employeeResponseDto;
        }


        return null;
    }

    @Override
    public EmployeeResponseDto deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            Employee employeeFromDb = employeeOptional.get();
            employeeRepository.deleteById(id);

            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeFromDb, employeeResponseDto);
            return employeeResponseDto;

            }
        return null;
    }
}
