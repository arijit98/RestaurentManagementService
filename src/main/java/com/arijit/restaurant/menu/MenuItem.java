package com.arijit.restaurant.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    private UUID itemId;
    private String itemName;
    private String itemDescription;
    private Double itemPrice;
    private UUID restaurantId;

}
