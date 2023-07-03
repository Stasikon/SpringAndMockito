package com.example.homeworktest2.Controller;

import com.example.homeworktest2.Domain.Employee;
import com.example.homeworktest2.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
    @RequestMapping("/employee")
    public class EmployeeController {
        private final EmployeeService servise;


        public EmployeeController(EmployeeService servise) {
            this.servise = servise;
        }
        @GetMapping("/add")
        public Employee addEmployee(@RequestParam String name, @RequestParam String surname,
                                    @RequestParam Integer department,@RequestParam Integer salary) {
            return servise.add(name, surname,department,salary);
        }
        @GetMapping("/remove")
        public Employee  removeEmployee(@RequestParam String name,@RequestParam String surname) {
            return servise.remove(name, surname);
        }
        @GetMapping("/find")
        public Employee findEmployee(@RequestParam String name, @RequestParam String surname) {
            return servise.find(name, surname);
        }
        @GetMapping
        public Collection<Employee> findAll() {
            return servise.findAll();
        }
    }
