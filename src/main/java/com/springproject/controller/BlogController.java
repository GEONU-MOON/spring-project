package com.springproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {
    @GetMapping("blogpost")
    public String blogpost(){
        return "blogpost";
    }

    @GetMapping("bloglist")
    public String bloglist(){ return "bloglist";}
}
