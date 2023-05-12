package com.springproject.controller;


import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.domain.Members;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MembersController {

    private final MemberService memberservice;

    @PostMapping("/")
    public String register(RegisterDTO form){
        Members member = new Members();
        member.setUserid(form.getUserid());
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setGithubLink(form.getGithubLink());

        memberservice.register(member);
        return "redirect:/";
    }

    @PostMapping("/home")
    public String Login(LoginDTO form, HttpServletRequest request) {
        Members result = memberservice.login(form);
        if(result != null){
            HttpSession session = request.getSession();
            session.setAttribute("userSession", result);
            session.setMaxInactiveInterval(60*10);
        }

        return "home";
    }
}
