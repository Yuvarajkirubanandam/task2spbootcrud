package com.yuvarajk.service;

import java.util.List;
import java.util.Optional;

import com.yuvarajk.Repository.DepartmentRepository;
import com.yuvarajk.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuvarajk.Repository.UserRepository;
import com.yuvarajk.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
   @Autowired
   private UserRepository userrepository;
   @Autowired
   private DepartmentRepository departmentRepository;


   @Override
    public User createUser(User user) {
        return userrepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userrepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userrepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userrepository.findById(user.getId()).get();
        existingUser.setFirstname(user.getFirstname());
        //existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setProfilepicture(user.getProfilepicture());
        existingUser.setDepartment(user.getDepartment());
        User updatedUser = userrepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userrepository.deleteById(userId);
    }



}

