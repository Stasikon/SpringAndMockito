package com.example.homeworktest2.Controller;

import com.example.homeworktest2.Domain.Employee;
import com.example.homeworktest2.Service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;


    @RestController
    @RequestMapping("/departmentOld")
    public class DepartmentController {
        private final DepartmentService departmentService;

        public DepartmentController(DepartmentService departmentService) {
            this.departmentService = departmentService;
        }

        @GetMapping("/max-salaryOld")
        public Optional<Employee> maxDepSalary(@RequestParam("departmentId") Integer department) {
            return departmentService.maxDepSalary(department);
        }

        @GetMapping("/min-salaryOld")
        public Optional<Employee> minDepSalary(@RequestParam("departmentID") Integer department) {
            return departmentService.minDepSalary(department);
        }

        @GetMapping(value = "/allOld", params = {"departmentID"})
        public List<Employee> printEmployeeToDepartment(@RequestParam("departmentID") Integer department) {
            return departmentService.printEmployeeToDepartment(department);
        }

        @GetMapping("/allOld")
        public Map<Integer, List<Employee>> printAllEmployee() {
            return departmentService.printAllEmployee();
        }
    }
