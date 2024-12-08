package com.example.capstone2foodtruck.Service;


import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.Employee;
import com.example.capstone2foodtruck.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }


    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }


    public void updateEmployee(Integer id,Employee employee){
        Employee oldEmployee=employeeRepository.findEmployeeById(id);

        if(oldEmployee==null){
            throw new ApiExcepiton("id not found");
        }

        oldEmployee.setName(employee.getName());
        oldEmployee.setFoodTruckId(employee.getFoodTruckId());
        oldEmployee.setRole(employee.getRole());
        oldEmployee.setSalary(employee.getSalary());
        employeeRepository.save(oldEmployee);
    }


    public void deleteEmployee(Integer id){

        Employee oldEmployee=employeeRepository.findEmployeeById(id);

        if(oldEmployee==null){
            throw new ApiExcepiton("id not found");
        }

        employeeRepository.delete(oldEmployee);

    }
}
