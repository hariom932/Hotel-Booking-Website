package com.example.oyoHotelproject.Services;

import com.example.oyoHotelproject.Entity.Resturent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RestaurantService extends CrudRepository<Resturent,Integer> {
}
