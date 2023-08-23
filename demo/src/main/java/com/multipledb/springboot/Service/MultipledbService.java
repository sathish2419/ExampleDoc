package com.multipledb.springboot.Service;

import com.multipledb.springboot.Primary.Employee;
import com.multipledb.springboot.Repository.EmployeeRepository;
import com.multipledb.springboot.Repository.ManagerRepository;
import com.multipledb.springboot.Secondary.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultipledbService {
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public ManagerRepository managerRepository;

    public List<Employee> saveEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public List<Manager> saveManagers(List<Manager> managers) {
        return managerRepository.saveAll(managers);
    }
}
