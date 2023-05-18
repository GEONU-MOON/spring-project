package com.springproject.DTO;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import java.time.LocalDate;

public class CommentDTO {

    private Board board;

    private Members member;

    private String content;

    private LocalDate postDate;
}
