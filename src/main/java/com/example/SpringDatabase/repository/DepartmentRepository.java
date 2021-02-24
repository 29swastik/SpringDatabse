package com.example.SpringDatabase.repository;

import com.example.SpringDatabase.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long>  {
}
