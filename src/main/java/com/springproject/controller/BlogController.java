package com.springproject.controller;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BoardService boardService;
    @GetMapping("blogpost")
    public String blogpost() {
        return "blogpost";
    }

    @GetMapping("bloglist")
    public String bloglist(HttpSession session, Model model) {
        Members member = (Members) session.getAttribute("Member");
        List<Board> userBoards = boardService.findList(member.getUserid());
        model.addAttribute("userBoards", userBoards);
        return "bloglist";
    }
}
