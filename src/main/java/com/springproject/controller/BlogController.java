package com.springproject.controller;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String blogList(Model model, HttpSession session,
                           @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {
        Members member = (Members) session.getAttribute("Member");
        Page<Board> userBoards = boardService.findPagedUserBoards(member.getUserid(), currentPage, pageSize);
        model.addAttribute("userBoards", userBoards);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", userBoards.getTotalPages());
        return "bloglist";
    }
}
