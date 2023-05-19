package com.springproject.controller;

import com.springproject.DTO.CommentDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Comments;
import com.springproject.domain.Members;
import com.springproject.repository.CommentRepository;
import com.springproject.service.BoardService;
import com.springproject.service.CommentService;
import com.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentRepository commentRepository;

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

    @GetMapping("/comments/{id}")
    public String getComments(@ModelAttribute CommentDTO commentDTO ,@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(commentDTO.getBoardId());
        Members member = memberService.findMemberById(commentDTO.getMemberId());
        if (board == null || member == null) {
            return "redirect:/";
        }

        List<CommentDTO> comments = commentService.getCommentsByBoardId(id);

        model.addAttribute("board", board);
        model.addAttribute("comments", comments);
        model.addAttribute("commentDTO", new CommentDTO());

        return "blogpost";
    }

    public List<Comments> getCommentsByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

}

