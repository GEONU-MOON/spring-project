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
    public String register(RegisterDTO form, String userid , HttpServletResponse response){
        boolean exist = memberService.findMembersByuserId(userid);
        if (!exist) {
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                String message = "존재하는 유저아이디입니다.";

                w.println("<script language='javascript'>");
                w.println("alert('" + message + "'); location.href='/';");
                w.println("</script>");

                w.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            Members member = new Members();
            member.setUserid(form.getUserid());
            member.setName(form.getName());
            member.setEmail(form.getEmail());
            member.setPassword(form.getPassword());
            member.setGithubLink(form.getGithubLink());

            memberService.register(member);

            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                String message = "회원가입이 완료되었습니다.";

                w.println("<script language='javascript'>");
                w.println("alert('" + message + "'); location.href='/';");
                w.println("</script>");

                w.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "redirect:/";
        }
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
        memberService.updateMember(member, form, session);
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
