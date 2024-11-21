package com.example.oyoHotelproject.Controllers;

import java.sql.*;
import java.util.ArrayList;
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

import com.example.oyoHotelproject.Entity.Hotel;
import com.example.oyoHotelproject.Entity.MyPackage;
import com.example.oyoHotelproject.Services.PackageService;

import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/Hotel")
public class HotelController
{
    @Autowired
    PackageService packageService;


    @RequestMapping("/login")
    public String login()
    {
        return "hotel/login.html";

    }

    @PostMapping("/login")
    public String home(HttpSession session,@RequestParam("username") String username,@RequestParam("password") String password)
    {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/hotelproject", "root", "1234");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from hotel where hoteluser='"+username+"' and password='"+password+"'");
            if(rs.next())
            {
                Hotel hotel = new Hotel();
                    hotel.setHid(rs.getInt("hid"));
                    hotel.setName(rs.getString("name"));
                    hotel.setAddress(rs.getString("address"));
                    hotel.setCity(rs.getString("city"));
                    hotel.setDescription(rs.getString("description"));
                    hotel.setContact(rs.getString("contact"));
                    hotel.setHoteluser(rs.getString("hoteluser"));
                    hotel.setPassword(rs.getString("password"));
                    hotel.setPhoto(rs.getString("photo"));
                    hotel.setStar(rs.getInt("star"));
                session.setAttribute("hotel", hotel);
                return  "redirect:/Hotel/addpackage";
            }
            else
            {
                return "redirect:/Hotel/login";
            }


        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "none";
    }
    
    @RequestMapping("/addpackage")
    public String add()
    {
        return "hotel/addPackage.html";
    }
    @PostMapping("/addpackage")
    public String save(MyPackage package1,HttpSession session)
    {
        Hotel hotel=(Hotel) session.getAttribute("hotel");
        package1.setHid(hotel.getHid());          
        packageService.save(package1);
        return "redirect:/Hotel/displaypackage";
    }


    @RequestMapping("/displaypackage")
    public String displaypackage(Model model,HttpSession session)
    {
        try
        {
        List<MyPackage> data=(List<MyPackage>)packageService.findAll();
        List<MyPackage> temp=new ArrayList<MyPackage>();
        Hotel hotel=(Hotel)session.getAttribute("hotel");
        int hid=hotel.getHid();
        for(var item : data)
        {
            if(item.getHid()==hid)
            {
                temp.add(item);
            }
        }
        model.addAttribute("data", temp);
        return "hotel/dispPackage.html";  
        }
        catch(Exception e)
        {
            return "redirect:/Hotel/logout";
        }

    }

    @GetMapping("/delete/{pid}")
    @ResponseBody
    public String delete(@PathVariable int pid)  
    {
        packageService.deleteById(pid);
        return "redirect:/Hotel/dispPackage";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/Hotel/login";
    }

}
