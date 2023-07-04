package com.example.homeworktest2.Service;

import com.example.homeworktest2.Domain.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NewDepartmentService {

    public List<Employee> printEmployeeToDepartment(int id);

    public double sumDepSalary(int id);

    public Optional<Employee> maxDepSalary(int id);

    public Optional<Employee> minDepSalary(int id);

    public Map<Integer, List<Employee>> departmentGrouped();


}
