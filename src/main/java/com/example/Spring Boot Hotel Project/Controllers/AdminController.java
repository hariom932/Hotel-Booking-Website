package com.example.oyoHotelproject.Controllers;

import jakarta.servlet.http.HttpSession;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.oyoHotelproject.Entity.Hotel;
import com.example.oyoHotelproject.Entity.HotelData;
import com.example.oyoHotelproject.Entity.Resturent;
import com.example.oyoHotelproject.Services.HotelService;
import com.example.oyoHotelproject.Services.RestaurantService;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    HotelService hotelService;
    
    @Autowired
    RestaurantService resturentServicess;

    @RequestMapping({"/","/login"})
    public String form()
    {
        return "admin/login.html";
    }

    @PostMapping("/login")
    // @ResponseBody
    public String home(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session)
    {
        if(username.equals("admin") && password.equals("admin123"))
        {

            session.setAttribute("admin", "Admin");
            System.out.println(username);
            return "redirect:/admin/home";   /*redirect to dashboard */
        }
        else
        {
            return "redirect:/admin/login";
        }
    }
    @RequestMapping("/home")
    public String home(HttpSession session)
    {
        if(session.getAttribute("admin")==null)
        {
            return "redirect:/admin/login";
            
        }
            return "admin/dashboard.html";
    }
    @RequestMapping("/addhotel") 
    public String addHotel(HttpSession session)
    {
        System.out.println(session.getAttribute("admin"));
        if(session.getAttribute("admin")==null)
        {
            return "redirect:/admin/login";
        }
        return "admin/addHotel.html";
    }
    @PostMapping("/addhotel")
    public String save(HotelData hotelData,HttpSession session)
    {
        if(session.getAttribute("admin")==null)
        {
            return "redirect:/admin/login";
        }
       try {
             Hotel hotel = new Hotel(hotelData);
            byte by[]=hotelData.getPhoto().getBytes();
            Path path = Paths.get("src/main/resources/static/hotel/" + hotel.getPhoto());
            Files.write(path, by);
            hotelService.save(hotel);
        } 
        catch (Exception e) 
        {
            return e.getMessage();
        }
        return "redirect:/admin/displayHotel";
    }
      @RequestMapping("/displayHotel")
       public String display(Model model,HttpSession session)
        {
            if(session.getAttribute("admin")==null)
            {
                return "redirect:/admin/login";
            }
            try
            {
                List<Hotel> info=(List<Hotel>)hotelService.findAll();
                model.addAttribute("hotel",info);
                return "admin/dispHotel.html";
            }
            catch(Exception e)
            {
                return e.getMessage();
            }
    }
    @GetMapping("/delete/{hid}")
    public String remove(@PathVariable int hid,HttpSession session)
    { 
        System.out.println(hid);
        if(session.getAttribute("admin")==null)  
        {
            return "redirect:/admin/login"; 
        }
        resturentServicess.deleteById(hid);
        return "redirect:/admin/displayHotel";
    }
    
    @RequestMapping("/addRestaurant")
    public String addRestaurant(HttpSession session)
    {
        if(session.getAttribute("admin")==null)
        {
            return "redirect:/admin/login";
        }
        return "admin/addRestaurant.html";
    } 

    @PostMapping("/addRestaurant")
    public String save(Resturent resturent,HttpSession session)
    { 
        if(session.getAttribute("admin")==null)
        {
            return "redirect:/admin/login";
        }
        resturentServicess.save(resturent);
        return "redirect:/admin/displayRestaurant";   
    }
    @RequestMapping("/displayRestaurant")
    public String displayRes(Model model,HttpSession session)
    {
        if(session.getAttribute("admin")==null)
        {
            return "redirect:/admin/login";
        }
             List<Hotel> info=(List<Hotel>)hotelService.findAll();
             model.addAttribute("data",info);
             return "admin/dispRes.html";
 }
 @RequestMapping("/logout")
 public String logout(HttpSession session)
 {
     session.invalidate();
     return "redirect:/admin/login";
 }
   
}
