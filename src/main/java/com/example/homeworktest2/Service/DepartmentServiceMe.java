package com.example.homeworktest2.Service;



import com.example.homeworktest2.Domain.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceMe implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceMe(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> printEmployeeToDepartment(Integer department) {
        return employeeService.findAll()
                .stream()
                .filter(person->person.getDepartment()== department)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Map<Integer, List<Employee>> printAllEmployee(){
        Map<Integer, List<Employee>> collect = employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return collect;
    }

    @Override
    public Optional<Employee> minDepSalary(Integer department) {
        return employeeService.findAll().stream()
                .filter(person -> person.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary));

    }
    @Override
    public Optional<Employee> maxDepSalary(Integer department) {
        return employeeService.findAll().stream()
                .filter(person -> person.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary));

    }
}