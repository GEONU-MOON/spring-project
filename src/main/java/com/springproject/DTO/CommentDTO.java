package com.springproject.DTO;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CommentDTO {

    private Long boardId;

    private Long memberId;

    private String content;

    private LocalDate postDate;

}

