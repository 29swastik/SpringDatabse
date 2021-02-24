package com.example.SpringDatabase.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.EnumMap;
import java.util.List;

@Entity
@Data // similar to getter and setter(instead writing both write @Data)
public class Department {
    @Id
    @GenericGenerator(name = "department_id_seq", strategy = "increment")
    @GeneratedValue(generator = "department_id_seq", strategy = GenerationType.AUTO)


    private long deptId;
    private String deptName;

    @JoinColumn(referencedColumnName = "deptId", name = "department_dept_id")
    @OneToMany
    List<Employee> employeeList;

}
