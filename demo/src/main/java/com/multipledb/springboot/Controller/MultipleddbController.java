package com.multipledb.springboot.Controller;

import com.multipledb.springboot.Primary.Employee;
import com.multipledb.springboot.Secondary.Manager;
import com.multipledb.springboot.Service.MultipledbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mdp")
public class MultipleddbController {

    @Autowired
    public MultipledbService multipledbService;

    @PostMapping("/addProduct_By_List")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return multipledbService.saveEmployees(employees);
    }
    @PostMapping("/manager")
    public List<Manager> addManagers(@RequestBody List<Manager> managers) {
        return multipledbService.saveManagers(managers);
    }
}
