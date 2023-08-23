package com.Spring.OnetoOne.Controller;


import com.Spring.OnetoOne.Entity.User;
import com.Spring.OnetoOne.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/us_id")
    public ResponseEntity<User> getUserById(@PathVariable Long us_id) {
        User user = userRepository.getReferenceById(us_id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping // Removed "user" from the path
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
