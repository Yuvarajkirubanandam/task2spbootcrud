package com.yuvarajk.Controller;
import com.yuvarajk.entity.Department;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.yuvarajk.entity.User;
import com.yuvarajk.service.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController

@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userservice;
    @PostMapping
    public ResponseEntity<CustomResponse<User>> createUser(User user, MultipartFile file) {
        try {

            Path uploadDir = Path.of("src/main/resources/static/uploads");
            Files.createDirectories(uploadDir);
            // Generate a unique file name
            String fileName = file.getOriginalFilename();
            // Save the uploaded file to the server
            Path filePath = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String url = "http://localhost:8080/uploads/" + fileName;
            user.setProfilepicture(url);
            User savedUser = userservice.createUser(user);
            CustomResponse<User> response = new CustomResponse<>(true, "data posted successfully!", savedUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            CustomResponse<User> errorResponse = new CustomResponse<>(false, "Error creating user", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
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

        } catch (Exception e) {
            CustomResponse<List<User>> errorResponse = new CustomResponse<>(false, "Error fetching data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }


    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, User user) {
        user.setId(userId);
        User updatedUser = userservice.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);


    }


    @DeleteMapping("{id}")
    public ResponseEntity<CustomResponse<String>> deleteUser(@PathVariable("id") Long userId) {
        try {
            userservice.deleteUser(userId);
            CustomResponse<String> response = new CustomResponse<>(true, "User Deleted successfully!", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            CustomResponse<String> errorResponse = new CustomResponse<>(false, "Error deleting user", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}




//    @PutMapping("{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, User user,MultipartFile file){
//        user.setId(userId);
//        try{
//            Path uploadDir = Path.of("src/main/resources/static/uploads");
//            Files.createDirectories(uploadDir);
//            String filename=file.getOriginalFilename();
//            Path filePath = uploadDir.resolve(filename);
//
//
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//            String url="http://localhost:8080/uploads/"+filename;
//            user.setProfilepicture(url);
//            User updatedUser = userservice.updateUser(user);
//            CustomResponse<User> userResponse=new CustomResponse<>(true,"Data has been Updated",updatedUser);
//            return ResponseEntity.ok(userResponse);
//        }
//        catch(Exception e){
//
//            CustomResponse<User> userResponse=new CustomResponse<>(false,"Failed to update the data",null);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponse);
//
//        }
//
//    }
//

    //....
//    public ResponseEntity<CustomResponse<User>> createUser(User user,MultipartFile file) {
//        try {
//
//            Path uploadDir = Path.of("src/main/resources/static/uploads");
//            Files.createDirectories(uploadDir);
//            // Generate a unique file name
//            String fileName = file.getOriginalFilename();
//            // Save the uploaded file to the server
//            Path filePath = uploadDir.resolve(fileName) ;
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//            String url = "http://localhost:8080/uploads/"+fileName;
//            user.setProfilepicture(url);
//            User savedUser = userservice.createUser(user);
//            CustomResponse<User> response = new CustomResponse<>(true, "data posted successfully!", savedUser);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//            CustomResponse<User> errorResponse = new CustomResponse<>(false, "Error creating user", null);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }

//    @PostMapping
//    public ResponseEntity<CustomResponse<User>> createUser(@RequestBody User user) {
//        try {
//            User savedUser = userservice.createUser(user);
//            CustomResponse<User> response = new CustomResponse<>(true, "data posted successfully!", savedUser);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//            CustomResponse<User> errorResponse = new CustomResponse<>(false, "Error creating user", null);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }












