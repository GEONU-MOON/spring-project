package com.springproject.controller;

import com.springproject.DTO.CommentDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.service.BoardService;
import com.springproject.service.CommentService;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping("/reply/write")
    public String writeReply(@ModelAttribute CommentDTO commentDTO, HttpSession session) {
        Board board = boardService.findBoardById(commentDTO.getBoardId());
        Members member = memberService.findMemberById(commentDTO.getMemberId());
        if (board == null || member == null) {
            return "error";
        }
        commentService.saveComment(commentDTO, board, member);
        return "redirect:/blogpost/" + commentDTO.getBoardId();
    }
}

