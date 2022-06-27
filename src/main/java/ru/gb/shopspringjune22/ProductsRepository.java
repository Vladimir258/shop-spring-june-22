package ru.gb.shopspringjune22;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductsRepository {

    private List<Product> items;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>(List.of(
                new Product(1L, "Monitor"),
                new Product(2L, "Mouse"),
                new Product(3L, "Keyboard")
        ));
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Product getItemById(Long id) {
        return items.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void add(Product product) {
        product.setId(items.size()+1L);
        items.add(product);
    }

    public void deleteAllItems() {
        items.clear();
    }

    public void deleteItemsById(Long id) {
       Product prod = items.stream()
               .filter(p -> p.getId().equals(id))
               .findFirst()
               .orElseThrow(() -> new RuntimeException("Product not found"));
       items.remove(prod);
    }
}
