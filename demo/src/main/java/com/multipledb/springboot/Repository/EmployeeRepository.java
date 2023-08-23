package com.multipledb.springboot.Repository;

import com.multipledb.springboot.Primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}