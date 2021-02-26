package com.example.SpringDatabase.service.impl;

import com.example.SpringDatabase.dto.DepartmentRequestDto;
import com.example.SpringDatabase.dto.DepartmentResponseDto;
import com.example.SpringDatabase.entity.Department;
import com.example.SpringDatabase.entity.Employee;
import com.example.SpringDatabase.repository.DepartmentRepository;
import com.example.SpringDatabase.repository.EmployeeRepository;
import com.example.SpringDatabase.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

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

    @Override
    @Transactional
    public DepartmentResponseDto updateDepartment(Long departmentId, DepartmentRequestDto departmentRequestDto) {
        Department department = departmentRepository.findById(departmentId).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_DeptId(departmentId);

        //update department
        department.setDeptName(departmentRequestDto.getDeptName());
     //   department.setDepartmentCode(departmentRequestDto.getDepartmentCode());
        Department savedDepartment = departmentRepository.save(department);

       // employeeList.forEach(employee -> {
         //   employee.setCode(departmentRequestDto.getDepartmentCode());
       // });

        employeeRepository.saveAll(employeeList);

        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment, departmentResponseDto);

        return departmentResponseDto;
    }

    @Override
    public List<DepartmentResponseDto> getDepartmentWithMaxExperienceSum() {
        List<Long> longList = departmentRepository.getDeptWithMaxSum();

        List<DepartmentResponseDto> departmentResponseDtos = new ArrayList<>();
        for(Long lon : longList){
            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
            Department department = departmentRepository.findById(lon).get();
            BeanUtils.copyProperties(department, departmentResponseDto);
            departmentResponseDtos.add(departmentResponseDto);
        }

        return departmentResponseDtos;
    }
}
