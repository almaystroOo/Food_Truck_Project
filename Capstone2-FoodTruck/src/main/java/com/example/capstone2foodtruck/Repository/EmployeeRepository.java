package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findEmployeeById(Integer id);

    List<Employee> findAll();
}
