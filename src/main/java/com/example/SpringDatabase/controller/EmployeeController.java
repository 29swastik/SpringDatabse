package com.example.SpringDatabase.controller;

import com.example.SpringDatabase.dto.EmployeeRequestDto;
import com.example.SpringDatabase.dto.EmployeeResponseDto;
import com.example.SpringDatabase.entity.Employee;
import com.example.SpringDatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //POST - /employee
    @PostMapping
    public EmployeeResponseDto createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return employeeService.createEmployee(employeeRequestDto);
    }

    // GET - /employee/{id}
    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable("id") long id){
        return employeeService.getEmployeeById(id);
    }

    //PUT - /employee/{id}
    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable("id") long id, @RequestBody EmployeeRequestDto employeeRequestDto){
        return employeeService.updateEmployeeById(id, employeeRequestDto);
    }

    @DeleteMapping("/{id}")
    public EmployeeResponseDto deleteEmployeeById(@PathVariable("id") Long id){
        return employeeService.deleteEmployeeById(id);
    }

    //GET - /employee/department/{id}
    @GetMapping("/department/{id}")
    public List<EmployeeResponseDto> getEmployeeListByDepartment(@PathVariable("id") Long id){
        return employeeService.getEmployeeListByDepartment(id);
    }

    @GetMapping("/employee/mostExperienced")
    public List<EmployeeResponseDto> getMostExperiencedEmployee(){
        return employeeService.getMostExperiencedEmployee();
    }

    @GetMapping("/department/{deptId}/employee/mostExperienced")
    public List<EmployeeResponseDto> getMostExperiencedEmployeeInDepartment(@PathVariable("deptId") Long id){
        return employeeService.getMostExperiencedEmployeeInDepartment(id);
    }

}
