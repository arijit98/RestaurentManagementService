package com.arijit.restaurant.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findOrCreateUser(String email, String name, String role) {
        Optional<User> optionalUser = repository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get(); // Return existing user
        }
        // Create new user
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setRoles(role);
        return repository.save(newUser);
    }
}
