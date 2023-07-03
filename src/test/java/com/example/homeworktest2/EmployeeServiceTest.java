package com.example.homeworktest2;

import com.example.homeworktest2.Domain.Employee;
import com.example.homeworktest2.Exeption.EmployeeAlreadyAddedException;
import com.example.homeworktest2.Exeption.EmployeeNotFoundException;
import com.example.homeworktest2.Exeption.EmployeeStorageIsFullException;
import com.example.homeworktest2.Service.EmployeeService;
import com.example.homeworktest2.Service.EmployeeServiceMe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

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
        employeeService.add("Pol", "Ninkiy", 1, 35000);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.add("Pol", "Ninkiy", 1, 35000));
    }
    @Test
    public void testAddEmployeeMaxListException() {
        employeeService.add("Pol", "Ninkiy", 1, 65000);
        employeeService.add("Ne", "Tolsty", 1, 55000);
        employeeService.add("Nina", "Kotova", 1, 30000);
        employeeService.add("Albert", "Entertain", 2, 20000);
        employeeService.add("Ilon", "Mask", 3, 100000);

        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add("Veaceslav", "Skarseze", 1, 35000));
        assertEquals(EmployeeServiceMe.MAX_COUNT, employeeService.findAll().size());
    }
    @Test
    public void testRemove() {
        employeeService.add("Tom", "Cruise", 1, 25000);
        employeeService.remove("Tom", "Cruise");
        assertEquals(0, employeeService.findAll().size());
    }
    @Test
    public void testRemoveException() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove("Tom", "Cruise"));
    }
    @Test
    public void testFindEmployee() {
        employeeService.add("Tony", "Stark", 1, 75000);
        employeeService.add("Piter", "Parker", 1, 35000);
        Employee findEmployee = employeeService.find("Tony", "Stark");
        assertNotNull(findEmployee);
        assertEquals("Tony", findEmployee.getName());
        assertEquals("Stark", findEmployee.getSurname());
    }
    @Test
    public void testFindAll() {
        employeeService.add("Pol", "Ninkiy", 1, 65000);
        employeeService.add("Ne", "Tolsty", 1, 55000);
        employeeService.add("Nina", "Kotova", 1, 30000);
        employeeService.add("Albert", "Entertain", 2, 20000);

        Collection<Employee> allEmployees = employeeService.findAll();
        assertEquals(4, allEmployees.size());

        assertTrue(allEmployees.contains(new Employee("Pol", "Ninkiy")));
        assertTrue(allEmployees.contains(new Employee("Ne", "Tolsty")));
        assertTrue(allEmployees.contains(new Employee("Nina", "Kotova")));
        assertTrue(allEmployees.contains(new Employee("Albert", "Entertain")));
    }
}
