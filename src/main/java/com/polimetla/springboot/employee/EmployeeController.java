package com.polimetla.springboot.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employees")
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable int employeeId) {
        employeeService.updateEmployee(employee, employeeId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
