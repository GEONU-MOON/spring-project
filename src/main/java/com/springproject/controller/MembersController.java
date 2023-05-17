package com.springproject.controller;


import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.DTO.UpdateDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MembersController {

    private final MemberService memberService;
    private final BoardService boardService;

    @PostMapping("/")
    public String register(RegisterDTO form){
        Members member = new Members();
        member.setUserid(form.getUserid());
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setGithubLink(form.getGithubLink());

        memberService.register(member);
        return "redirect:/";
    }

    @GetMapping("about")
    public String about(Model model){
        model.addAttribute("UpdateDTO", new UpdateDTO());

        return "about";
    }

    @PostMapping("/update")
    public String updateMember(UpdateDTO form, HttpSession session) {
        Members member = (Members) session.getAttribute("Member");
        memberService.updateMember(member, form);
        session.setAttribute("Member", member);
        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String deleteMember(HttpSession session) {
        Members member = (Members) session.getAttribute("Member");
        memberService.deleteMember(member);
        session.invalidate();
        return "redirect:/";
    }
}
