package com.ecommerce.user.controller;

import com.ecommerce.user.model.User;
import com.ecommerce.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }
    @GetMapping("/details")
    public ResponseEntity<Map<String,Object>> details()
    {
        return ResponseEntity.ok(
                userservice.getUserandProducts()
        );
    }

    @GetMapping("/list")
    public List<User> users()
    {
        return userservice.findAll();
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        userservice.addUser(user);
        return ResponseEntity.ok("User added successfully"+user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
    {
        userservice.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }



}
