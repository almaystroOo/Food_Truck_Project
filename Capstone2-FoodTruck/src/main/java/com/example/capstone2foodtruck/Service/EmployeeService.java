package com.example.capstone2foodtruck.Service;


import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.Employee;
import com.example.capstone2foodtruck.Model.FoodTruck;
import com.example.capstone2foodtruck.Repository.EmployeeRepository;
import com.example.capstone2foodtruck.Repository.FoodTruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final FoodTruckRepository foodTruckRepository;

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee, Integer foodTruckId) {
        FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
                .orElseThrow(() -> new ApiExcepiton("Food truck not found"));
        employee.setFoodTruck(foodTruck);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer id, Employee employee) {
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("Employee not found"));

        oldEmployee.setName(employee.getName());
        oldEmployee.setRole(employee.getRole());
        oldEmployee.setSalary(employee.getSalary());

        if (employee.getFoodTruck() != null) {
            FoodTruck foodTruck = foodTruckRepository.findById(employee.getFoodTruck().getId())
                    .orElseThrow(() -> new ApiExcepiton("Food truck not found"));
            oldEmployee.setFoodTruck(foodTruck);
        }

        employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(Integer id) {
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ApiExcepiton("Employee not found"));

        employeeRepository.delete(oldEmployee);
    }
}

