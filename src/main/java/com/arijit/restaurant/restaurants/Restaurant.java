package com.arijit.restaurant.restaurants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private UUID id;
    private String name;
    private String address;
    private String contactNumber;
    private UUID individualId;
    private String logoUrl;
}
