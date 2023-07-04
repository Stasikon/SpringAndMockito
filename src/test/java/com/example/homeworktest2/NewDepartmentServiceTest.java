package com.example.homeworktest2;

import com.example.homeworktest2.Domain.Employee;
import com.example.homeworktest2.Service.EmployeeService;
import com.example.homeworktest2.Service.NewDepartmentServiceMe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NewDepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private NewDepartmentServiceMe newDepartmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void setEmployeeService(List<Employee> employees) {
        when(employeeService.findAll()).thenReturn(employees);
    }
    private void verifyFindAllEmployeesCalledOnce() {
        verify(employeeService, times(1)).findAll();
    }
    private List<Employee> completedListEmployee() {
        return new ArrayList<>(Arrays.asList(
                new Employee("Pol", "Ninkiy", 1, 60000),
                new Employee("Ne", "Tolsty", 1, 15000),
                new Employee("Nina", "Kotova", 1, 30000),
                new Employee("Albert", "Entertain", 2, 20000),
                new Employee("Ilon", "Mask", 3, 70000),
                new Employee("Tom", "Cruise", 1, 25000)
        ));
    }
    @Test
    public void testGetEmployeesByDepartment() {
        // Arrange
        int departmentId = 1;
        List<Employee> employees = completedListEmployee();
        setEmployeeService(employees);

        // Act
        List<Employee> result = newDepartmentService.printEmployeeToDepartment(departmentId);

        // Assert
        verifyFindAllEmployeesCalledOnce();
        assertEquals(4, result.size());
        for (Employee employee : result) {
            assertEquals(departmentId, employee.getDepartment());
        }
    }
   @Test
   public void testGetAllEmployeeEmptyDepartment() {
       int departmentId = 1;
       List<Employee> employees = new ArrayList<>();

       setEmployeeService(employees);

       List<Employee> result = newDepartmentService.printEmployeeToDepartment(departmentId);
       assertEquals(employees, result);

       verifyFindAllEmployeesCalledOnce();
   }
    @Test
    public void testGetSalarySumByDepartment() {

        int departmentId = 1;
        int invalidDepartmentId = 0;


        List<Employee> employees = completedListEmployee();

        setEmployeeService(employees);


        double result = newDepartmentService.sumDepSalary(departmentId);
        double invalidResult = newDepartmentService.sumDepSalary(invalidDepartmentId);

        assertEquals(130000, result);
        assertEquals(0, invalidResult);

        verify(employeeService, times(2)).findAll();
    }
    @Test
    public void testGetMaxSalary() {

        int departmentId = 1;
        List<Employee> employees = completedListEmployee();

        setEmployeeService(employees);

        Optional<Employee> resultMaxSalary = newDepartmentService.maxDepSalary(departmentId);


        assertTrue(resultMaxSalary.isPresent());
        assertEquals(60000, resultMaxSalary.get().getSalary());

        verifyFindAllEmployeesCalledOnce();
    }
    @Test
    public void testGetMinSalary() {
        int departmentId = 1;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Pol", "Ninkiy", 1, 60000));
        employees.add(new Employee("Ne", "Tolsty", 1, 15000));
        employees.add(new Employee("Nina", "Kotova", 1, 30000));

        setEmployeeService(employees);
        Optional<Employee> resultMaxSalary = newDepartmentService.minDepSalary(departmentId);

        assertTrue(resultMaxSalary.isPresent());
        assertEquals(15000, resultMaxSalary.get().getSalary());

        verifyFindAllEmployeesCalledOnce();
    }
    @Test
    public void departmentGroupedTest() {
        Employee employee1 = new Employee("Pol", "Ninkiy", 1, 60000);
        Employee employee2 = new Employee("Ne", "Tolsty", 2, 15000); // Значение department изменено на 2
        Employee employee3 = new Employee("Nina", "Kotova", 1, 30000);
        List<Employee> allEmployees = Arrays.asList(employee1, employee2, employee3);

        setEmployeeService(allEmployees);


        Map<Integer, List<Employee>> result = newDepartmentService.departmentGrouped();

        verifyFindAllEmployeesCalledOnce();
        assertEquals(2, result.size());
        assertEquals(Arrays.asList(employee1, employee3), result.get(1));
        assertEquals(Arrays.asList(employee2), result.get(2)); // Значение изменено на 2
    }
}
