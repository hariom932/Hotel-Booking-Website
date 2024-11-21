package com.example.oyoHotelproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resturent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private  String name,category,address,city,contact,description,password,username;


    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Resturent [id=" + id + ", name=" + name + ", category=" + category + ", address=" + address + ", city="
                + city + ", contact=" + contact + ", description=" + description + ", password=" + password
                + ", username=" + username + "]";
    }

    public Resturent(String name, String category, String address, String city, String contact, String description) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.city = city;
        this.contact = contact;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Resturent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
