package com.springproject.controller;

import com.springproject.DTO.BoardDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.repository.MembersRepository;
import com.springproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MembersRepository membersRepository;
    @GetMapping("boardwrite")
    public String boardwrite(Model model){
        model.addAttribute("boardForm", new BoardDTO());

        return "boardwrite";
    }

    @PostMapping("boardwrite")
    public String boardregister(BoardDTO form, HttpSession session){
        Board board = new Board();
        board.setMember(boardService.setEmbedMember(((Members)session.getAttribute("Member")).getUserid()));
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        board.setPostDate(LocalDate.now());
        board.setThumbnail(form.getThumbnail());

        boardService.save(board);
        return "home";
    }

    @GetMapping("boardupdate")
    public String boardupdate() {return "boardupdate";}

    @GetMapping("userboard")
    public String userBoard(HttpSession session, Model model){
        Members member = (Members) session.getAttribute("Member");
        List<Board> userBoards = boardService.findList(member.getUserid());
        model.addAttribute("userBoards", userBoards);
        return "userboard";
    }

}
