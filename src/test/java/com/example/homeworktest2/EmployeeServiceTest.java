package com.example.homeworktest2;

import com.example.homeworktest2.Domain.Employee;
import com.example.homeworktest2.Exeption.EmployeeAlreadyAddedException;
import com.example.homeworktest2.Service.EmployeeService;
import com.example.homeworktest2.Service.EmployeeServiceMe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceMe();
    }

    @Test
    public void testAddEmployee() {
        Employee result = employeeService.add("Pol", "Ninkiy",1,35000);
        assertNotNull(result);
        assertEquals(1, employeeService.findAll().size());
    }
    @Test
    public void testAddEmployeeDuplicateException() {
        //тест, что при добавлении дубликата выскакивает ошибка
        employeeService.add("Игорь", "Васильев", 1, 25000);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.add("Игорь", "Васильев", 1, 25000));
    }
}
