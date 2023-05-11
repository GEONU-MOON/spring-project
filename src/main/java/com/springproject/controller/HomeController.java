package com.springproject.controller;

import com.springproject.DTO.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("registerform", new RegisterDTO());
        return "index";
    }

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("blogpost")
    public String blogpost(){
        return "blogpost";
    }


    @GetMapping("about")
    public String about(){
        return "about";
    }

    @GetMapping("bloglist")
    public String bloglist(){ return "bloglist";}

    @GetMapping("boardWrite")
    public String boardWrite() {return "boardWrite";}

    @GetMapping("boardupdate")
    public String boardupdate() {return "boardupdate";}

    @PostMapping("/home")
    public String Login() {
        return "home";
    }

    @PostMapping("/register")
    public String register(@RequestParam("registerId")String Id,
                           @RequestParam("registerName")String name,
                           @RequestParam("registerEmail")String email,
                           @RequestParam("registerPassword")String password){

        return "redirect:/";
    }
}
