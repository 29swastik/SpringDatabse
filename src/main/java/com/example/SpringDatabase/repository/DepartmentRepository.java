package com.example.SpringDatabase.repository;

import com.example.SpringDatabase.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long>  {

    @Query(value = "select department_dept_id from deptsum where sum >= all (select sum from deptsum);", nativeQuery = true)
   // @Query(value = "SELECT department_dept_id from department", nativeQuery = true)
    public List<Long> getDeptWithMaxSum();

}
