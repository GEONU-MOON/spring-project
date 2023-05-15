package com.springproject.controller;


import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/update")
    public String updateMember(RegisterDTO form, HttpSession session) {
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

    @PostMapping("/home")
    public String Login(LoginDTO form, Model model, HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(name = "userid")String userid) {
        String inputID = form.getUserid();
        String inputPW = form.getPassword();

        String homeurl = "" + userid;
        Members result = memberService.login(inputID, inputPW);
        if(result != null){
            HttpSession session = request.getSession();
            session.setAttribute("Member", result);
            session.setMaxInactiveInterval(60*10);

            List<Board> boardList = boardService.findList(inputID);
            model.addAttribute("boardlist", boardList);

            System.out.println(boardList.isEmpty());

        }else{
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.println("<script language='javascript'>");
                w.println("alert('로그인에 실패하였습니다. 다시 시도해주세요'); location.href='/';");
                w.println("</script>");

                w.flush();
                //실패시
                return "redirect:/";
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "home";
    }

    @RequestMapping(value="logout.do", method= RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
