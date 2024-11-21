package com.example.oyoHotelproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MyPackage 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;
    private int hid;
    private String name,description,amount;

    

    public MyPackage(int pid, int hid, String name, String description, String amount) {
        this.pid = pid;
        this.hid = hid;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
    public MyPackage() {
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getHid() {
        return hid;
    }
    public void setHid(int hid) {
        this.hid = hid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Package [pid=" + pid + ", hid=" + hid + ", name=" + name + ", description=" + description + ", amount="
                + amount + "]";
    }
    

    
}
