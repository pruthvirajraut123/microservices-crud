package com.ecommerce.user.service;

import com.ecommerce.user.exception.ResourceNotFoundException;
import com.ecommerce.user.external.ProductFeignClient;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;
    private ProductFeignClient productFeignClient;

    public UserService(UserRepository userRepository,ProductFeignClient productFeignClient) {
        this.userRepository = userRepository;
        this.productFeignClient=productFeignClient;
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User addUser( User user)
    {
        return userRepository.save(user);
    }

    public void deleteUser(Long id)
    {
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        userRepository.deleteById(id);
    }

    @CircuitBreaker(name="productService",fallbackMethod="fallbackProducts")
    public Map<String,Object> getUserandProducts()
    {
       List<User> users=userRepository.findAll();
       Map<String,Object> products=productFeignClient.getProducts();
       Map<String,Object> response=new HashMap<>();
       response.put("users",users);
       response.put("products",products);
       return response;
    }

    public Map<String,Object> fallbackProducts(Exception ex)
    {
        Map<String,Object> response=new HashMap<>();
        response.put("message","Product service temporarily unavailable");
        return response;
    }
}
