package com.example.capstone2foodtruck.Service;


import com.example.capstone2foodtruck.ApiResponse.ApiExcepiton;
import com.example.capstone2foodtruck.Model.Product;
import com.example.capstone2foodtruck.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService{

    private final ProductRepository productRepository;


    public List<Product> getProduct(){
        return productRepository.findAll();
    }


    public void addProduct(Product product){
        productRepository.save(product);
    }


    public void updateProduct(Integer id,Product product){
        Product oldProduct=productRepository.findProductsById(id);

        if(oldProduct==null){
            throw new ApiExcepiton("id not found");
        }

        oldProduct.setFoodTruckId(product.getFoodTruckId());
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCost(product.getCost());
        oldProduct.setAvailability(product.getAvailability());
        productRepository.save(oldProduct);
    }


    public void deleteProduct(Integer id){

        Product oldProduct=productRepository.findProductsById(id);

        if(oldProduct==null){
            throw new ApiExcepiton("id not found");
        }

        productRepository.delete(oldProduct);

    }


    public List<Product> suggestMealsByPrice(Double maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }

    private int purchaseCount = 0;

    public double purchaseProduct(Integer productId) {
        purchaseCount++;

        Product product = productRepository.findProductsById(productId);
        if (product != null) {
            double price = product.getPrice();
            double finalPrice = price;


            if (purchaseCount == 2) {
                finalPrice = price * 0.9; // 10% discount
                System.out.println("Discount applied! Final price: " + finalPrice);
            } else {
                System.out.println("Final price: " + finalPrice);
            }

            return finalPrice;
        } else {
            throw new ApiExcepiton("Product not found");
        }
    }

    public double calculateAverageProductPrice(Integer foodTruckId) {
        Double averagePrice = productRepository.getAveragePriceByFoodTruckId(foodTruckId);
        if (averagePrice == null) {
            return 0.0;
        }
        return averagePrice;
    }

    public String countAvailableAndUnavailableProducts(Integer foodTruckId) {
        int availableCount = productRepository.countByFoodTruckIdAndAvailability(foodTruckId, "Available");
        int unavailableCount = productRepository.countByFoodTruckIdAndAvailability(foodTruckId, "Unavailable");

        String result = "Available: " + availableCount + ", Unavailable: " + unavailableCount;
        return result;
    }



}
