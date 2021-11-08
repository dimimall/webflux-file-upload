package com.vinsguru.webfluxfileupload.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Table("products")
public class Product {

    @Id
    private Long id;

    @Column("itemName")
    private String itemName;

    @Nullable
    @Column("priceProduct")
    private String productPrice;

    public Product(String itemName, String productPrice) {
        this.itemName = itemName;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setItemName(String name){
        this.itemName = name;
    }

    public void setProductPrice(String price){
        this.productPrice = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                itemName.equals(product.itemName) &&
                productPrice.equals(product.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, productPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}
