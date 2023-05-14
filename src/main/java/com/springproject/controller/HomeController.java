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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("LoginForm", new LoginDTO());
        model.addAttribute("registerForm", new RegisterDTO());
        return "index";
    }

    @GetMapping("blogpost")

    public String blogpost(HttpSession session) {
        Members member = (Members) session.getAttribute("Member");
        if (member != null) {
            return "blogpost";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("about")
    public String about(HttpSession session) {
        Members member = (Members) session.getAttribute("Member");
        if (member != null) {
            return "about";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("bloglist")
    public String bloglist(HttpSession session) {
        Members member = (Members) session.getAttribute("Member");
        if (member != null) {
            return "bloglist";
        } else {
            return "redirect:/";
        }
    }




    @GetMapping("home")
    public String home(Model model, HttpSession session){
        Members member = (Members) session.getAttribute("Member");
        if (member != null) {
            List<Board> boardList = boardService.findList(member.getUserid());
            model.addAttribute("boardList", boardList);
            return "home";
        } else {
            return "redirect:/";
        }
    }


}
