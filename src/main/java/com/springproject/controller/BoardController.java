package com.springproject.controller;

import com.springproject.DTO.BoardDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("boardWrite")
    public String boardWrite(Model model){
        model.addAttribute("boardForm", new BoardDTO());

        return "boardWrite";
    }

    @PostMapping("boardWrite")
    public String boardregister(BoardDTO form, HttpSession session){
        Board board = new Board();
        board.setMember((Members) session.getAttribute("Member"));
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        board.setPostDate(LocalDate.now());
        board.setThumbnail(form.getThumbnail());


        return null;
    }

    @GetMapping("boardupdate")
    public String boardupdate() {return "boardupdate";}
}
