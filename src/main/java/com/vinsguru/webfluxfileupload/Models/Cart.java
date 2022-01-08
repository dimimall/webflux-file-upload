package com.vinsguru.webfluxfileupload.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Table("cart")
public class Cart {

    @Id
    private Long id;

    @Column("product_id")
    private String productId;

    @Column("user_id")
    private Long userId;

    @Column("product_name")
    private String productName;

    @Column("product_price")
    private String productPrice;

    @Column("quantity")
    private String quantity;

    public Cart(String productId, Long userId, String productName, String productPrice, String quantity) {
        this.productId = productId;
        this.userId = userId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return getId().equals(cart.getId()) &&
                getProductId().equals(cart.getProductId()) &&
                getUserId().equals(cart.getUserId()) &&
                getProductName().equals(cart.getProductName()) &&
                getProductPrice().equals(cart.getProductPrice()) &&
                getQuantity().equals(cart.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId(), getUserId(), getProductName(), getProductPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", userId=" + userId +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
