package com.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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


    public String blogpost(){
        return "blog-post";
    }

    public String bloglist(){
        return "blog-list";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }
}
