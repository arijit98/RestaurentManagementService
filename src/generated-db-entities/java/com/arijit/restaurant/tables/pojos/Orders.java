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
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private Integer tableId;
    private String customerName;
    private String status;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Orders() {}

    public Orders(Orders value) {
        this.id = value.id;
        this.tableId = value.tableId;
        this.customerName = value.customerName;
        this.status = value.status;
        this.totalPrice = value.totalPrice;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    @ConstructorProperties({ "id", "tableId", "customerName", "status", "totalPrice", "createdAt", "updatedAt" })
    public Orders(
        UUID id,
        Integer tableId,
        String customerName,
        String status,
        Double totalPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.tableId = tableId;
        this.customerName = customerName;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>public.orders.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.orders.id</code>.
     */
    public Orders setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.orders.table_id</code>.
     */
    public Integer getTableId() {
        return this.tableId;
    }

    /**
     * Setter for <code>public.orders.table_id</code>.
     */
    public Orders setTableId(Integer tableId) {
        this.tableId = tableId;
        return this;
    }

    /**
     * Getter for <code>public.orders.customer_name</code>.
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * Setter for <code>public.orders.customer_name</code>.
     */
    public Orders setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    /**
     * Getter for <code>public.orders.status</code>.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>public.orders.status</code>.
     */
    public Orders setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Getter for <code>public.orders.total_price</code>.
     */
    public Double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Setter for <code>public.orders.total_price</code>.
     */
    public Orders setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    /**
     * Getter for <code>public.orders.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>public.orders.created_at</code>.
     */
    public Orders setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>public.orders.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>public.orders.updated_at</code>.
     */
    public Orders setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
        final Orders other = (Orders) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.tableId == null) {
            if (other.tableId != null)
                return false;
        }
        else if (!this.tableId.equals(other.tableId))
            return false;
        if (this.customerName == null) {
            if (other.customerName != null)
                return false;
        }
        else if (!this.customerName.equals(other.customerName))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.totalPrice == null) {
            if (other.totalPrice != null)
                return false;
        }
        else if (!this.totalPrice.equals(other.totalPrice))
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
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.tableId == null) ? 0 : this.tableId.hashCode());
        result = prime * result + ((this.customerName == null) ? 0 : this.customerName.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.totalPrice == null) ? 0 : this.totalPrice.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Orders (");

        sb.append(id);
        sb.append(", ").append(tableId);
        sb.append(", ").append(customerName);
        sb.append(", ").append(status);
        sb.append(", ").append(totalPrice);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
