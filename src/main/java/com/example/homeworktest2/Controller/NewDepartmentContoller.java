package com.example.homeworktest2.Controller;

import com.example.homeworktest2.Domain.Employee;
import com.example.homeworktest2.Service.NewDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class NewDepartmentContoller {
    private final NewDepartmentService newDepartmentService;

    @Autowired
    public NewDepartmentContoller(NewDepartmentService departmentService) {
        this.newDepartmentService = departmentService;
    }
    @GetMapping("/{id}/employees")
    public List<Employee> printEmployeeToDepartment(@PathVariable int id) {
        return newDepartmentService.printEmployeeToDepartment(id);
    }
    @GetMapping("/{id}/salary/sum")
    public double sumDepSalary(@PathVariable int id) {
        return newDepartmentService.sumDepSalary(id);
    }
    @GetMapping("/{id}/salary/max")
    public Optional<Employee> maxDepSalary(@PathVariable int id) {
        return newDepartmentService.maxDepSalary(id);
    }
    @GetMapping("/{id}/salary/min")
    public Optional<Employee> minDepSalary(@PathVariable int id) {
        return newDepartmentService.minDepSalary(id);
    }
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> departmentGrouped() {
        return newDepartmentService.departmentGrouped();
    }

}

