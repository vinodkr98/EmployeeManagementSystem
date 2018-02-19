package com.test.ems.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.test.ems.model.Employee;
import com.test.ems.service.EmployeeService;
 
@RestController
public class EmployeeController {
 
	
    public EmployeeController() {
        System.out.println("EmployeeController()");
    }
 
    @Autowired
    private EmployeeService employeeService;
 
    @RequestMapping(value = "/")
    public ModelAndView listEmployee(ModelAndView model) throws IOException {
        List<Employee> listEmployee = employeeService.getAllEmployees();
        model.addObject("listEmployee", listEmployee);
        model.setViewName("home");
        return model;
    }
 
    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
    public ResponseEntity newContact(@RequestBody Employee employee) {
    	employeeService.addEmployee(employee);
        return new ResponseEntity(employee, HttpStatus.CREATED);
    }
 
    @RequestMapping(value = "/saveEmployee", method = RequestMethod.PUT)
    public ResponseEntity  saveEmployee(@ModelAttribute Employee employee) {
        if (employee.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            employeeService.addEmployee(employee);
        } else {
            employeeService.updateEmployee(employee);
        }
        return new ResponseEntity(employee, HttpStatus.ACCEPTED);
    }
 
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    public ResponseEntity  deleteEmployee(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
    @RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
    public ResponseEntity editContact(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployee(employeeId);
       
        return new ResponseEntity(HttpStatus.OK);
    }
 
}
