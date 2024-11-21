package com.example.oyoHotelproject.Services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.oyoHotelproject.Entity.Customer;

@Component
public interface CustomerService extends CrudRepository<Customer, Integer>
{
    
}
