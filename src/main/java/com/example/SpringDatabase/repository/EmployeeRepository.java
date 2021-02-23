package com.example.SpringDatabase.repository;

import com.example.SpringDatabase.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {}
