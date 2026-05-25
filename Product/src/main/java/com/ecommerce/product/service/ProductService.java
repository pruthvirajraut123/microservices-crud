package com.ecommerce.product.service;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository=productRepository;
    }

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }
    public Product addProduct(Product product)
    {
        return productRepository.save(product);
    }

    public Product deleteById(Long productId)
    {
       /* Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));*/
        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product Not Found"));
        productRepository.deleteById(productId);
         return product;
    }
}
