package com.example.SpringDatabase.dto;

import com.example.SpringDatabase.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private long id;
    private String name;
    private DepartmentResponseDto department;
    private String code;

    public void setDepartmentFromEntity(Department departmentFromEntity){
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setDeptId(departmentFromEntity.getDeptId());
        departmentResponseDto.setDeptName(departmentFromEntity.getDeptName());
        department = departmentResponseDto;

    }

}
