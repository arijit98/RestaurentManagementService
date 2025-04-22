package com.arijit.restaurant.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID individualId;
    private String email;
    private String name;
    private String password;
    private String roles;
}
