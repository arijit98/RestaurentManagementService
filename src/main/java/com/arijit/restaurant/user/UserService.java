package com.arijit.restaurant.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> findByUsername(String username) {
        return repository.findByName(username);
    }

    public User createUser(String name, String password, String role) {
        Optional<User> optionalUser = repository.findByName(name);
        if (optionalUser.isPresent()) {
            return optionalUser.get(); // Return existing user
        }
        // Create new user
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setName(name);
        newUser.setRoles(role);
        return repository.save(newUser);
    }

    public User createUser(User user) {
        return repository.save(user);
    }
}
