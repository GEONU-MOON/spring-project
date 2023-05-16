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
    public String blogpost(){
        return "blogpost";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }

    @GetMapping("bloglist")
    public String bloglist(){ return "bloglist";}

    @GetMapping("home")
    public String home(Model model, HttpSession session){
        Members member = (Members) session.getAttribute("Member");
        if (member != null) {
            List<Board> boardList = boardService.recentBoard(member.getUserid());
            model.addAttribute("recentBoard", boardList);
            return "home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("blogpost/{id}")
    public String blogpost(@PathVariable Long id, Model model){
        Board board = boardService.findBoardById(id);
        if (board == null) {
            return "redirect:/";
        }
        model.addAttribute("board", board);
        return "blogpost";
    }
}
