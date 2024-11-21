package com.example.oyoHotelproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel 
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int hid;
    private int star;
    private String name,address,city,description,contact,photo,hoteluser,password;


public Hotel(HotelData temp)
{
    
    this.name=temp.getName();
    this.address=temp.getAddress();
    this.contact= temp.getContact();
    this.city=temp.getCity();
    this.photo=temp.getPhoto().getOriginalFilename();
    this.star=temp.getStar();
    this.description = temp.getDescription();
    this.hoteluser=temp.getHoteluser();
    this.password=temp.getPassword();

}

@Override
    public String toString() {
        return "Hotel [hid=" + hid + ", star=" + star + ", name=" + name + ", address=" + address + ", city=" + city
                + ", description=" + description + ", contact=" + contact + ", photo=" + photo + ", hoteluser="
                + hoteluser + ", password=" + password + "]";
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



public int getHid() {
    return hid;
}

public void setHid(int hid) {
    this.hid = hid;
}

public int getStar() {
    return star;
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

public String getPhoto() {
    return photo;
}

public void setPhoto(String photo) {
    this.photo = photo;
}

public Hotel() {
}

}
