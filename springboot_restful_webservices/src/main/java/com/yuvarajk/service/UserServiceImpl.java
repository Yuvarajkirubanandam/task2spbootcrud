package com.yuvarajk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yuvarajk.Repository.UserRepository;
import com.yuvarajk.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   private UserRepository userrepository;

   
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
        existingUser.setFirstname(user.getFirstname());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userrepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userrepository.deleteById(userId);
    }

    @Override
    public List<User> getUserById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

  
    
}
