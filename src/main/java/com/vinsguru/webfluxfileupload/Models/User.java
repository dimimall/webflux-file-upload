package com.vinsguru.webfluxfileupload.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.Objects;

//create cart model and teble database
// with id auto increment user name user role user password
// these are a columns in database and properties in class User
@Table("users")
public class User {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("role")
    private String role;

    @Column("password")
    private String password;

    //constractor User Model
    public User(String name, String role, String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public User() {
    }

    //setter and getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //create classes inherited from super class Object equals, hashcode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
