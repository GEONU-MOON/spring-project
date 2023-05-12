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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

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
    public String Login(LoginDTO form, HttpServletRequest request, HttpServletResponse response) {
        String inputID = form.getUserid();
        String inputPW = form.getPassword();

        Members result = memberservice.login(inputID, inputPW);
        if(result != null){
            HttpSession session = request.getSession();
            session.setAttribute("Member", result);
            session.setMaxInactiveInterval(60*10);
        }else{
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.println("<script language='javascript'>");
                w.println("alert('로그인에 실패하였습니다. 다시 시도해주세요'); location.href='/';");
                w.println("</script>");

                w.flush();
                return "redirect:/";
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "home";
    }
}
