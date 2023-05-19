package com.springproject.service;

import com.springproject.DTO.CommentDTO;
import com.springproject.domain.Board;
import com.springproject.domain.Comments;
import com.springproject.domain.Members;
import com.springproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(CommentDTO commentDTO, Board board, Members member) {
        Comments comment = new Comments();
        comment.setBoard(board);
        comment.setMember(member);
        comment.setContent(commentDTO.getContent());
        comment.setPostDate(LocalDate.now());

        commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentsByBoardId(Long boardId) {
        List<Comments> comments = commentRepository.findByBoardId(boardId);
        List<CommentDTO> commentDTOs = new ArrayList<>();

        for (Comments comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setBoardId(comment.getBoard().getId());
            commentDTO.setMemberId(comment.getMember().getId());
            commentDTO.setContent(comment.getContent());
            commentDTO.setPostDate(comment.getPostDate());
            commentDTOs.add(commentDTO);
        }

        return commentDTOs;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
