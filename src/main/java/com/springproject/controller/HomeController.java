package com.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
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

}
