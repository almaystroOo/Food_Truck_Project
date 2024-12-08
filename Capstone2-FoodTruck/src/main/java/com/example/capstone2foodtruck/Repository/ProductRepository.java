package com.example.capstone2foodtruck.Repository;

import com.example.capstone2foodtruck.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

     Product findProductsById(Integer id);

     List<Product> findByPriceLessThan(Double maxPrice);
}
