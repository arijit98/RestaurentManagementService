package com.arijit.restaurant.menu;


public class RequestMapper {
    private RequestMapper() {}
    public static MenuItem mapToMenuItem(MenuRequest menu) {
        return MenuItem.builder()
                .itemName(menu.getItemName())
                .itemDescription(menu.getItemDescription())
                .itemPrice(menu.getItemPrice())
                .build();
    }
}
