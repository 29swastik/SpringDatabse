package com.example.SpringDatabase.controller;

import com.example.SpringDatabase.dto.DepartmentRequestDto;
import com.example.SpringDatabase.dto.DepartmentResponseDto;
import com.example.SpringDatabase.entity.Department;
import com.example.SpringDatabase.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDto createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto){
        return departmentService.createDepartment(departmentRequestDto);
    }

    @GetMapping("/{deptId}")
    public Department getDepartmentById(@PathVariable("deptId") Long id){
        return departmentService.getDepartmentById(id);
    }

    @PutMapping({"/{deptId}"})
    public DepartmentResponseDto updateDepartment(@PathVariable("deptId") Long departmentId, @RequestBody DepartmentRequestDto departmentRequestDto){
        return departmentService.updateDepartment(departmentId, departmentRequestDto);
    }

    @GetMapping("department/mostExperienced")
    public List<DepartmentResponseDto> getDepartmentWithMaxExperienceSum(){
        return departmentService.getDepartmentWithMaxExperienceSum();
    }
}
