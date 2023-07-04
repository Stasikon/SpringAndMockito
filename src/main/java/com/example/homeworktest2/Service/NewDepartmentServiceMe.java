package com.example.homeworktest2.Service;

import com.example.homeworktest2.Domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewDepartmentServiceMe implements NewDepartmentService {
    private final EmployeeService employeeService;

    @Autowired
    public NewDepartmentServiceMe(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public List<Employee> printEmployeeToDepartment(int id) {
        List<Employee> allEmployees = (List<Employee>) employeeService.findAll();
        return allEmployees.stream().filter(employee -> employee.getDepartment() == id)
                .collect(Collectors.toList());
    }
    @Override
    public double sumDepSalary(int id) {
        List<Employee> allEmployees = (List<Employee>) employeeService.findAll();
        List<Employee> employeesByDepartment = allEmployees.stream()
                .filter(employee -> employee.getDepartment() == id)
                .collect(Collectors.toList());
        return employeesByDepartment.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }
    @Override
    public Optional<Employee> maxDepSalary(int id) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == id)
                .max(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public Optional<Employee> minDepSalary(int id) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == id)
                .min(Comparator.comparingInt(Employee::getSalary));
    }
    @Override
    public Map<Integer, List<Employee>> departmentGrouped() {
        List<Employee> allEmployees = (List<Employee>) employeeService.findAll();
        return allEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
