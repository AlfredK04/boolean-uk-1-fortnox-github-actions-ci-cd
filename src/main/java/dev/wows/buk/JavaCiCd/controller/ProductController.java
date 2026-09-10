package dev.wows.buk.JavaCiCd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.wows.buk.JavaCiCd.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wows.buk.JavaCiCd.repository.ProductReposity;

import java.util.List;

@RestController 
@RequestMapping ("/api/products")
public class ProductController {
    private final ProductReposity productReposity;

    public ProductController(ProductReposity productReposity) {
        this.productReposity = productReposity;
    }

    @GetMapping 
    public List<Product> getAll() {
        return productReposity.findAll();
    }

    @PostMapping 
    public Product create(@RequestBody Product product) {
        return productReposity.save(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productReposity.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        return productReposity.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    return ResponseEntity.ok(productReposity.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!productReposity.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        productReposity.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}