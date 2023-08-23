package com.spring.mapping.Service;

import com.spring.mapping.Entity.User;
import com.spring.mapping.Repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public User getUserByName(String userName) { return userRepository.findByUserName(userName); }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }
    public User updateUser(Long userId, User newUser) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(newUser.getUserName());
            if (newUser.getAddress() != null) {
                existingUser.getAddress().setAddressCity(newUser.getAddress().getAddressCity());
                existingUser.getAddress().setAddressStreet(newUser.getAddress().getAddressStreet());
            }
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }
    public boolean deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return true; // Deletion successful
        }
        return false; // User not found
    }
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
