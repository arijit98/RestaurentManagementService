package com.arijit.restaurant.menu;


import java.util.UUID;

public class RequestMapper {
    private RequestMapper() {}
    public static MenuItem mapToMenuItem(MenuRequest menu, UUID restaurantId) {
        return MenuItem.builder()
                .restaurantId(restaurantId)
                .itemName(menu.getItemName())
                .itemDescription(menu.getItemDescription())
                .itemPrice(menu.getItemPrice())
                .build();
    }
}
