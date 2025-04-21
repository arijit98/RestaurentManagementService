package com.arijit.restaurant.menu;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class MenuRequest {
    private String itemName;
    private String itemDescription;
    private Double itemPrice;
    private String itemCategory;
    private String itemImageUrl;
}
