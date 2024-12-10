package com.example.capstone2foodtruck.Controller;

import com.example.capstone2foodtruck.Model.Employee;
import com.example.capstone2foodtruck.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get")
    public  ResponseEntity<?> get() {

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        employeeService.addEmployee(employee,employee.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Employee added");
    }

   @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
       employeeService.updateEmployee(id,employee);
        return ResponseEntity.status(HttpStatus.OK).body("Employee update");
    }


    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable Integer id) {

        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee delete");
    }

}
