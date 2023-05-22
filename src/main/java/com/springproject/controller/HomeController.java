package com.springproject.controller;

import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("LoginForm", new LoginDTO());
        model.addAttribute("registerForm", new RegisterDTO());
        return "index";
    }

    @GetMapping("/otherhome")
    public String otherhome(@RequestParam("otheruserid") String userid, Model model, HttpServletResponse response) {
        boolean exist = memberService.findMembersByuserId(userid);
        if (exist) {
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                String message = "존재하지 않는 유저아이디입니다.";

                w.println("<script language='javascript'>");
                w.println("alert('" + message + "'); location.href='/home';");
                w.println("</script>");

                w.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Members otherMember = memberService.findByID(userid);
            List<Board> boardList = boardService.recentBoard(otherMember.getUserid(), 3);

            model.addAttribute("recentBoard", boardList);
            model.addAttribute("otherMember", otherMember);
            return "otheruser/home";
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Members member = (Members) session.getAttribute("Member");
        if (member != null) {
            List<Board> boardList = boardService.recentBoard(member.getUserid(), 3);
            model.addAttribute("recentBoard", boardList);
        }
        return "home";
    }

    @PostMapping("/home")
    public String Login(LoginDTO form, Model model, HttpServletRequest request, HttpServletResponse response) {
        String inputID = form.getUserid();
        String inputPW = form.getPassword();

        Members result = memberService.login(inputID, inputPW);
        if (result != null) {
            HttpSession session = request.getSession();
            session.setAttribute("Member", result);
            session.setMaxInactiveInterval(60 * 10);

            List<Board> boardList = boardService.recentBoard(inputID, 3);

            model.addAttribute("recentBoard", boardList);

            return "home";
        } else {
            return "redirect:/";
        }
    }


    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

}
