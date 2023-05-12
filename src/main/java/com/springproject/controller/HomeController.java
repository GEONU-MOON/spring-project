package com.springproject.controller;

import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("LoginForm", new LoginDTO());
        model.addAttribute("registerForm", new RegisterDTO());
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




}
