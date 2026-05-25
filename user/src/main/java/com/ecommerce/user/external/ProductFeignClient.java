package com.ecommerce.user.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(
        name = "PRODUCT-SERVICE",
        url = "http://localhost:8081"
)
public interface ProductFeignClient {
    @GetMapping("/products/list")
    Map<String,Object> getProducts();
}
