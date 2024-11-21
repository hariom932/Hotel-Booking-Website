package com.example.oyoHotelproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class RestaurantDishes
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rid;
    private int resId;
    private int price;
    private String name,dish,description;
    

    
    public RestaurantDishes(int rid, int resId, int price, String name, String dish, String description) {
        this.rid = rid;
        this.resId = resId;
        this.price = price;
        this.name = name;
        this.dish = dish;
        this.description = description;
    }
    public RestaurantDishes() {
    }
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDish() {
        return dish;
    }
    public void setDish(String dish) {
        this.dish = dish;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getResId() {
        return resId;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "RestaurantDishes [rid=" + rid + ", resId=" + resId + ", price=" + price + ", name=" + name + ", dish="
                + dish + ", description=" + description + "]";
    }
    
    
}
