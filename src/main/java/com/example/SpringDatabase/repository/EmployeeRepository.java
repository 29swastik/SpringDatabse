package com.example.SpringDatabase.repository;

import com.example.SpringDatabase.entity.Department;
import com.example.SpringDatabase.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {

    //way 1 to list employee by dept using Method
    List<Employee> findByDepartment(Department department);

    //way 2 to list employee by dept using Method
    List<Employee> findByDepartment_DeptId(Long departmentId);

    //way 3 to list employee by dept using @Query
    @Query("SELECT e FROM Employee e WHERE e.department.id = ?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentId);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
    List<Employee> getEmployeeListByDepartmentIdParam(@Param("deptId") Long departmentId);

    @Query(value = "SELECT * FROM employee e WHERE e.department_dept_id = ?1", nativeQuery = true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);
}
