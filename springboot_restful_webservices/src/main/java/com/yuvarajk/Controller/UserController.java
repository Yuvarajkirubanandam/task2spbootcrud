package com.yuvarajk.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.yuvarajk.entity.User;
import com.yuvarajk.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController

@RequestMapping("api/users")
public class UserController
{
    @Autowired
    private UserService userservice;
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User  savedUser = userservice.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

 
    @GetMapping("{id}")
public ResponseEntity<CustomResponse<User>> getUserById(@PathVariable("id") Long userId) {
    try {
        // Assuming your service method is designed to fetch a user by ID
        User user = userservice.getUserById(userId);

        if (user != null) {
            CustomResponse<User> response = new CustomResponse<>(true, "Data fetched successfully", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            CustomResponse<User> errorResponse = new CustomResponse<>(false, "User not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    } catch (Exception e) {
        CustomResponse<User> errorResponse = new CustomResponse<>(false, "Error fetching data", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
   
   @GetMapping("all")
    public ResponseEntity<CustomResponse<List<User>>> getAllUsers() {
        
        try {
            List<User> userList = userservice.getAllUsers();
            CustomResponse<List<User>> response = new CustomResponse<>(true, "data fetched successfully", userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e)
        {
            CustomResponse<List<User>> errorResponse = new CustomResponse<>(false, "Error fetching data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    
        
    }
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,@RequestBody User user){
        user.setId(userId);
        User updatedUser = userservice.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userservice.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

}
    











