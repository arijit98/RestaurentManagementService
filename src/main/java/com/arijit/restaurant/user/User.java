package com.arijit.restaurant.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 50)
    private String email;
    private String name;
    private String password;
    private String roles;
}
