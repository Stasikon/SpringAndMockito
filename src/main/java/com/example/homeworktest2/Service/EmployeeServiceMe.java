package com.example.homeworktest2.Service;


import com.example.homeworktest2.Domain.Employee;
import com.example.homeworktest2.Exeption.EmployeeAlreadyAddedException;
import com.example.homeworktest2.Exeption.EmployeeNotFoundException;
import com.example.homeworktest2.Exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeServiceMe implements EmployeeService {

    public static final int MAX_COUNT = 5;
    private final List<Employee> employeeList;

    public EmployeeServiceMe() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String name, String surname,int department,int salary) {
        Employee employee = new Employee(name, surname,department,salary);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        if (employeeList.size() >= MAX_COUNT) {
            throw new EmployeeStorageIsFullException("Достигнута максимальная вместимость списка сотрудников, новый сотрудник не добавлен");
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String name, String surname, int department, int salary) {
        Employee employee = new Employee(name, surname,department,salary);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        employeeList.add(employee);
        throw new EmployeeNotFoundException("Такого сотрудника нету");

    }

    @Override
    public Employee find(String name, String surname,int department,int salary) {
        Employee employee = new Employee(name, surname,department,salary);
        if (employeeList.contains(employee)) {
            return employee;
        }
        employeeList.add(employee);
        throw new EmployeeNotFoundException("Такого сотрудника нету");
    }
    @Override
    public Collection<Employee> findAll() {
        return  Collections.unmodifiableList(employeeList);
    }
}

