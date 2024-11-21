package com.example.oyoHotelproject.Services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.oyoHotelproject.Entity.Hotel;

@Component
public interface HotelService extends CrudRepository<Hotel,Integer>
{
   
}
