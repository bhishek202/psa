package com.crm.crm.service;


import com.crm.crm.entity.Employee;
import com.crm.crm.exception.ResourceNotFound;
import com.crm.crm.payload.EmployeeDto;
import com.crm.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
   private EmployeeRepository employeeRepository;
   private ModelMapper modelMapper;


   public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
       this.employeeRepository=employeeRepository;
       this.modelMapper = modelMapper;
   }
   public EmployeeDto addEmployee(EmployeeDto dto){
       Employee employee= mapToEntity(dto);
       Employee emp=employeeRepository. save(employee);
      EmployeeDto employeeDto= mapToDto(emp);

       return employeeDto;
   }

    public void deleteEmployee(Long id) {
       employeeRepository.deleteById(id);
    }
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto){
       Employee employee= mapToEntity(dto);
       employee.setId(id);
        Employee updateEmployee =employeeRepository.save(employee);
        EmployeeDto employeeDto=mapToDto(updateEmployee);
        return employeeDto;

    }

    EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
//       EmployeeDto dto=new EmployeeDto();
//       dto.setId(employee.getId());
//       dto.setName(employee.getName());
//       dto.setEmailId(employee.getEmailId());
//       dto.setMobile(employee.getMobile());
       return dto;
    }
    Employee mapToEntity(EmployeeDto dto){
       Employee emp=modelMapper.map(dto,Employee.class);
//        Employee emp=new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        return emp;
    }


    public EmployeeDto getEmployeeById(long id) {
        Employee employee1 = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Record not found " + id)
        );
        EmployeeDto dto = mapToDto(employee1);
        return dto;
    }

    public List<EmployeeDto> getEmployee( int pageNo, int pageSize,String  sortBy,String sortDir) {
       //this code use int data sort asending and decending order
       Sort sort =sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       //get the the singal data and store the page me
       Pageable page = PageRequest.of(pageNo,pageSize,sort);
       //
        Page<Employee> all = employeeRepository.findAll(page);

        List<Employee> employee = all.getContent();

        List<EmployeeDto> dto = employee.stream().map(e -> mapToDto(e)).collect(Collectors.toList());

        return dto;
    }

}
