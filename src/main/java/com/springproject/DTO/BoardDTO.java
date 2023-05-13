package com.springproject.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter
public class BoardDTO {
    private String title;

    private String content;

    private LocalDate postDate;

    private String thumbnail;
}
