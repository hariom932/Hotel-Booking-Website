package com.example.oyoHotelproject.Entity;

import org.springframework.web.multipart.MultipartFile;

public class HotelData 
{
    private int star;
    private String name,address,city,description,contact,hoteluser,password;
    private MultipartFile photo;
    
    public int getStar() {
        return star;
    }
    public String getHoteluser() {
        return hoteluser;
    }
    public void setHoteluser(String hoteluser) {
        this.hoteluser = hoteluser;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setStar(int star) {
        this.star = star;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public MultipartFile getPhoto() {
        return photo;
    }
    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
