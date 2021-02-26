package com.example.SpringDatabase.service.impl;

import com.example.SpringDatabase.dto.DepartmentResponseDto;
import com.example.SpringDatabase.dto.EmployeeRequestDto;
import com.example.SpringDatabase.dto.EmployeeResponseDto;
import com.example.SpringDatabase.entity.Department;
import com.example.SpringDatabase.entity.Employee;
import com.example.SpringDatabase.repository.DepartmentRepository;
import com.example.SpringDatabase.repository.EmployeeRepository;
import com.example.SpringDatabase.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();

        //copy files from dto to employee
        BeanUtils.copyProperties(employeeRequestDto, employee);

        Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDto.getDepartment().getDeptId());
        if(departmentOptional.isPresent()){
            employee.setDepartment(departmentOptional.get());
        }

        else {
            Department department = new Department();
            department.setDeptName(employeeRequestDto.getDepartment().getDeptName());
            employee.setDepartment(department);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        //copy from Employee to responseDto
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(savedEmployee, employeeResponseDto);

        employeeResponseDto.setDepartmentFromEntity(savedEmployee.getDepartment());

        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            //copy from employye to employeeresponsedto
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(), employeeResponseDto);
            employeeResponseDto.setDepartmentFromEntity(employeeOptional.get().getDepartment());
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

            Optional<Department> optionalDepartment = departmentRepository.findById(employeeRequestDto.getDepartment().getDeptId());

            if(optionalDepartment.isPresent()){
                employeeFromDb.setDepartment(optionalDepartment.get());
            }

            else {
                Department department = new Department();
                department.setDeptName(employeeRequestDto.getDepartment().getDeptName());
                employeeFromDb.setDepartment(department);
            }

            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(savedEmployee, employeeResponseDto);

            employeeResponseDto.setDepartmentFromEntity(savedEmployee.getDepartment());

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

    @Override
    public List<EmployeeResponseDto> getEmployeeListByDepartment(Long id) {
       // Department department = departmentRepository.findById(id).get();                      // way1
      //  List<Employee> employeeList = employeeRepository.findByDepartment(department);        //way1

        List<Employee> employeeList = employeeRepository.findByDepartment_DeptId(id);           //way2

       // List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentId(id);

       // List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentIdParam(id);

       // List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(id);
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();

        for(Employee employee:employeeList){
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee, employeeResponseDto);
            employeeResponseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(employeeResponseDto);
        }

        return employeeResponseDtoList;
    }

    @Override
    public List<EmployeeResponseDto> getMostExperiencedEmployee() {
        List<Employee> employeeList = employeeRepository.getMostExperienced();
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();

        for(Employee employee : employeeList){
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee, employeeResponseDto);
            employeeResponseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(employeeResponseDto);
        }

        return employeeResponseDtoList;

    }

    @Override
    public List<EmployeeResponseDto> getMostExperiencedEmployeeInDepartment(Long id) {

        List<Employee> employeeList = employeeRepository.getMostExperiencedByDepartment(id);
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();

        for(Employee employee : employeeList){
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee, employeeResponseDto);
            employeeResponseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(employeeResponseDto);
        }

        return employeeResponseDtoList;

    }



}
