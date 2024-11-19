package com.crm.crm.controller;


import com.crm.crm.payload.EmployeeDto;
import com.crm.crm.service.EmployeeService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

//    http://localhost:8080/api/v1/employee/add
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
        @Valid @RequestBody EmployeeDto dto,
        BindingResult result

    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

      EmployeeDto employeeDto=  employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/employee?id=1
    @DeleteMapping()
    public ResponseEntity<EmployeeDto> deleteEmployee(EmployeeDto employeeDto
    ){
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/employee?id=1
    @PutMapping()
    public ResponseEntity<EmployeeDto> UpdateEmployee(
            @RequestParam("id") Long id,
            @RequestBody EmployeeDto dto
    ){
       EmployeeDto employeeDto= employeeService.updateEmployee(id,dto);
        return deleteEmployee(employeeDto);
    }
    //http://localhost:8080/api/v1/employee?pageSize=3&pageNo=1&sortBy=email
    @GetMapping("/employee2")
    public ResponseEntity<List<EmployeeDto>> getEmployee(
            @RequestParam(name = "pageSize",required = false,defaultValue ="3")int pageSize,
            @RequestParam(name ="pageNo",required = false,defaultValue="0")int pageNo,
            @RequestParam( name="sortBy",required = false,defaultValue="name")String sortBy,
            @RequestParam( name="sortDir",required = false,defaultValue="asc")String sortDir
    ){
        List<EmployeeDto> dto = employeeService.getEmployee(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<List<EmployeeDto>>(dto , HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/employee?id=1
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long id){

        EmployeeDto dto = employeeService.getEmployeeById(id);
        return  new ResponseEntity<EmployeeDto>(dto , HttpStatus.OK);
    }

}

