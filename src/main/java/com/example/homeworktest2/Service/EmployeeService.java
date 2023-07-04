package com.example.homeworktest2.Service;


import com.example.homeworktest2.Domain.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String name, String surname, int department, int salary);

    Employee remove(String name, String surname, int department, int salary);

    Employee find(String name, String surname, int department, int salary);

    Collection<Employee> findAll();
}

