package com.example.oyoHotelproject.Controllers;
import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.oyoHotelproject.Entity.RestaurantDishes;
import com.example.oyoHotelproject.Entity.Resturent;
import com.example.oyoHotelproject.Services.DishService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController
{
   

    @Autowired
    DishService restDishService;


 @RequestMapping("/login")
    public String login()
    {
        return"restaurant/resturantlogin.html";
    }


    @PostMapping("/login")
    public String home(HttpSession session,@RequestParam("username") String username,@RequestParam("password") String password)
    {
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/hotelproject", "root", "1234");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from resturent where username='"+username+"' and password='"+password+"'");
            if(rs.next())
            {

                Resturent resturent =new Resturent();
                resturent.setId(rs.getInt("Id"));
                resturent.setAddress(rs.getString("Address"));
                resturent.setCategory(rs.getString("Category"));
                resturent.setCity(rs.getString("City"));
                resturent.setName(rs.getString("Name"));
                resturent.setDescription(rs.getString("Description"));
                session.setAttribute("ResturentAdd",resturent);
                return"redirect:/restaurant/addDishes";

            }
            else
            {
                return "redirect:/restaurant/login";
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "redirect:/restaurant/login";
    }


    @RequestMapping("/addDishes")
    public String add()
    {
        return "restaurant/addRes.html";
    }
    @PostMapping("/addDishes")
    @ResponseBody
    public String save(RestaurantDishes myRestaurant,HttpSession session)
    {

        Resturent resturentAdd = (Resturent) session.getAttribute("ResturentAdd");
        myRestaurant.setResId(resturentAdd.getId());
        restDishService.save(myRestaurant);
        return "Done....";
    }

    @RequestMapping("/displayDishes")
    public String display(Model model)
    {
        List <RestaurantDishes> data =(List<RestaurantDishes>)restDishService.findAll();
         model.addAttribute("data",data);
        return "restaurant/dispResturent";
    }

        @GetMapping("/delete/{rid}")
    @ResponseBody
    public String delete(@PathVariable int rid)  
    {
        restDishService.deleteById(rid);
        return "data removed";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/restaurant/login";
    }

}
