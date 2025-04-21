package com.arijit.restaurant.restaurants;

import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantRequest {
    private String name;
    private String address;
    private String contactNumber;
    private String logoUrl;
}