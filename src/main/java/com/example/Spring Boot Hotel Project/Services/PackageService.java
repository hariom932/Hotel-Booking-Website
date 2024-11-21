package com.example.oyoHotelproject.Services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.oyoHotelproject.Entity.MyPackage;

@Component
public interface PackageService  extends CrudRepository<MyPackage,Integer>
{

    
} 