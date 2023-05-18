package com.springproject.controller;

import com.springproject.DTO.BoardDTO;
import com.springproject.DTO.CommentDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.repository.MembersRepository;
import com.springproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
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

    @GetMapping("boardupdate/{id}")
    public String boardupdate(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        if (board == null) {
            return "redirect:/";
        }
        model.addAttribute("board", board);
        return "boardupdate";
    }



    @GetMapping("blogpost/{id}")
    public String blogpost(@PathVariable Long id, Model model, HttpSession session){
        Board board = boardService.findBoardById(id);
        if (board == null) {
            return "redirect:/";
        }

        String userid = ((Members)session.getAttribute("Member")).getUserid();

        Long prevBoardId = boardService.findPrevBoardId(userid, id);
        Long nextBoardId = boardService.findNextBoardId(userid, id);

        model.addAttribute("board", board);
        model.addAttribute("prevBoardId", prevBoardId);
        model.addAttribute("nextBoardId", nextBoardId);
        model.addAttribute("commentDTO", new CommentDTO());

        return "blogpost";
    }


}
