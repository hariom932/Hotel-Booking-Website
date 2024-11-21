package com.example.oyoHotelproject.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.oyoHotelproject.Entity.Customer;
import com.example.oyoHotelproject.Entity.Hotel;
import com.example.oyoHotelproject.Entity.MyPackage;
import com.example.oyoHotelproject.Entity.RestaurantDishes;
import com.example.oyoHotelproject.Entity.Resturent;
import com.example.oyoHotelproject.Services.CustomerService;
import com.example.oyoHotelproject.Services.DishService;
import com.example.oyoHotelproject.Services.HotelService;
import com.example.oyoHotelproject.Services.RestaurantService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WebsiteController {

Connection con;
Statement st;


    public WebsiteController()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/hotelproject", "root","1234");
            st=con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
               
        }
    }
    
    @Autowired
    HotelService hotelService;

    @Autowired
    DishService dishService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CustomerService customerService;

    @RequestMapping({"/","/home","/index.html"})
    public String Home(Model model)
    {
        List <Hotel>  arr= (List<Hotel>) hotelService.findAll();   
        model.addAttribute("data", arr);
        List <Resturent> res=(List<Resturent>) restaurantService.findAll();
        model.addAttribute("res", res);
        return "website/index.html";
    }
    @RequestMapping({"/about","/about.html"})
    public String about()
    {
        return "website/about.html";
    }
    @RequestMapping({"/contact","/contact.html"})
    public String contact()
    {
        return "website/contact.html";
    }
    @RequestMapping({"/restaurant","restaurant.html"})
    public String restaurant(Model model)
    {
        List<RestaurantDishes> data= (List<RestaurantDishes>) dishService.findAll();
        model.addAttribute("data", data);
        for(RestaurantDishes item : data)
        {
            System.out.println(item.toString());     
        }
        return "website/restaurant.html";
    }
    @RequestMapping({"/hotel","/rooms.html"}) 
    public String hotel(Model model)
    {
        List <Hotel>  arr= (List<Hotel>) hotelService.findAll();
        model.addAttribute("data", arr);
        return "website/rooms.html";
    }

    @RequestMapping("/showdish/{resid}")
    public String showdish(@PathVariable("resid") int resid,Model model,HttpSession session)
    {
        if(session.getAttribute("customer")==null)
        {
            return "redirect:/login";
        }
        List<RestaurantDishes> data = new ArrayList<RestaurantDishes>();
         try
        {
            ResultSet rs=st.executeQuery("select * from restaurant_dishes where res_id="+resid);
            while(rs.next())
            {
                RestaurantDishes temp = new RestaurantDishes(rs.getInt("rid"),
                 rs.getInt("res_id"),
                 rs.getInt("price"), 
                 rs.getString("name"), 
                 rs.getString("dish"),
                 rs.getString("description"));
                data.add(temp);
                System.out.println(temp.toString());
            }
            model.addAttribute("dish", data);
            return "website/displaydish.html";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        
    }
    
    @RequestMapping("/displaypackages/{hid}")
    public String showpackages(@PathVariable("hid") int hid,Model model,HttpSession session)
    {
        if(session.getAttribute("customer")==null)
        {
            return "redirect:/login";
        }
        List<MyPackage> data=new ArrayList<MyPackage>();
        try
        {
            ResultSet rs=st.executeQuery("select * from my_package where hid="+hid);
            while(rs.next())
            {
                 MyPackage temp=new MyPackage(rs.getInt("pid"),
                 rs.getInt("hid"),
                 rs.getString("name"),
                 rs.getString("description"),
                 rs.getString("amount"));
                 data.add(temp);   
            }
            model.addAttribute("package", data);
            return "website/displaypackage.html";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/signup")
    public String signup()
    {
        return "/custSignup.html";
    }
    @PostMapping("/signup")
    @ResponseBody
    public String save(Customer customer)
    {
        customerService.save(customer);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "/loginCust.html"; 
    }
    @PostMapping("/login")
    public String loginCheck(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session)
    {     
        try{
            ResultSet rs=st.executeQuery("select * from customer where email='"+email+"' and password='"+password+"'");
            if(rs.next())
            {
                Customer loginCustomer = new Customer();
                loginCustomer.setCid(rs.getInt("cid"));
                loginCustomer.setEmail(rs.getString("email"));
                loginCustomer.setPassword(rs.getString("password"));
                loginCustomer.setUsername(rs.getString("username"));
                session.setAttribute("customer",loginCustomer);
                return "redirect:/home";
            }
            else
            {
                return "redirect:/login";
            }
        } 
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/home";
    }
}
