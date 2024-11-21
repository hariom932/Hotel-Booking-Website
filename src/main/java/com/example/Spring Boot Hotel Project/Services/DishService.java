package com.example.oyoHotelproject.Services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.oyoHotelproject.Entity.RestaurantDishes;
@Component
public interface DishService extends CrudRepository<RestaurantDishes,Integer>
{
    
}
