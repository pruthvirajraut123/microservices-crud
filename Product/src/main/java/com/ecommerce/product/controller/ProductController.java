package com.ecommerce.product.controller;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> getProducts()
    {
        List<Product> products=productService.findAll();
        Map<String,Object> response=new HashMap<>();
        response.put("message","Below are products present");
        response.put("data",products);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/addProduct")
    public ResponseEntity<Map<String,Object>> addProduct(@RequestBody Product product)
    {
       Product pro=productService.addProduct(product);
       Map<String,Object> response=new HashMap<>();
       response.put("Message","New added product is");
       response.put("data",pro);
       return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public ResponseEntity<Map<String,Object>> deleteProduct(@PathVariable Long productId )
    {
        Product product=productService.deleteById(productId);
        Map<String,Object> response=new HashMap<>();
        response.put("message","deleted product is");
        response.put("data",product);
        return ResponseEntity.ok(response);
    }


}
