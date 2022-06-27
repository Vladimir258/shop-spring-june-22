package ru.gb.shopspringjune22;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    private ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productsRepository.getItems();
    }

    @GetMapping("products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productsRepository.getItems()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping("/products")
    public void addNewProduct(@RequestBody Product product) {
        productsRepository.add(product);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {

    }



}
