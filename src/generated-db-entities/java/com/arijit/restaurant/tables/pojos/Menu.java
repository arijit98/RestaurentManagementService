/*
 * This file is generated by jOOQ.
 */
package com.arijit.restaurant.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.processing.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String itemName;
    private String status;
    private Double price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID restaurantId;

    public Menu() {}

    public Menu(Menu value) {
        this.id = value.id;
        this.itemName = value.itemName;
        this.status = value.status;
        this.price = value.price;
        this.description = value.description;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
        this.restaurantId = value.restaurantId;
    }

    @ConstructorProperties({ "id", "itemName", "status", "price", "description", "createdAt", "updatedAt", "restaurantId" })
    public Menu(
        UUID id,
        String itemName,
        String status,
        Double price,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID restaurantId
    ) {
        this.id = id;
        this.itemName = itemName;
        this.status = status;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.restaurantId = restaurantId;
    }

    /**
     * Getter for <code>public.menu.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.menu.id</code>.
     */
    public Menu setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.menu.item_name</code>.
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Setter for <code>public.menu.item_name</code>.
     */
    public Menu setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    /**
     * Getter for <code>public.menu.status</code>.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>public.menu.status</code>.
     */
    public Menu setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Getter for <code>public.menu.price</code>.
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * Setter for <code>public.menu.price</code>.
     */
    public Menu setPrice(Double price) {
        this.price = price;
        return this;
    }

    /**
     * Getter for <code>public.menu.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.menu.description</code>.
     */
    public Menu setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Getter for <code>public.menu.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>public.menu.created_at</code>.
     */
    public Menu setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>public.menu.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>public.menu.updated_at</code>.
     */
    public Menu setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * Getter for <code>public.menu.restaurant_id</code>.
     */
    public UUID getRestaurantId() {
        return this.restaurantId;
    }

    /**
     * Setter for <code>public.menu.restaurant_id</code>.
     */
    public Menu setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Menu other = (Menu) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.itemName == null) {
            if (other.itemName != null)
                return false;
        }
        else if (!this.itemName.equals(other.itemName))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.price == null) {
            if (other.price != null)
                return false;
        }
        else if (!this.price.equals(other.price))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!this.updatedAt.equals(other.updatedAt))
            return false;
        if (this.restaurantId == null) {
            if (other.restaurantId != null)
                return false;
        }
        else if (!this.restaurantId.equals(other.restaurantId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.itemName == null) ? 0 : this.itemName.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.restaurantId == null) ? 0 : this.restaurantId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Menu (");

        sb.append(id);
        sb.append(", ").append(itemName);
        sb.append(", ").append(status);
        sb.append(", ").append(price);
        sb.append(", ").append(description);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);
        sb.append(", ").append(restaurantId);

        sb.append(")");
        return sb.toString();
    }
}
