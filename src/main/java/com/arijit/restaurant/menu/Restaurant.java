package com.arijit.restaurant.menu;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
public class Restaurant {
    private UUID id;
    private String name;
    private String address;
    private String contactNumber;
    private String logoUrl;
}
