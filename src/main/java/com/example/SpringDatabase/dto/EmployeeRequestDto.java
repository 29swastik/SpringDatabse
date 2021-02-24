package com.example.SpringDatabase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {

    private long id;
    private String name;
    private DepartmentRequestDto department;
    private String code;
}
