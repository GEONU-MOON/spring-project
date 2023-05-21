package com.springproject.DTO;

import com.springproject.domain.EmbedMember;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Embedded;
import java.time.LocalDate;

@Getter @Setter
public class BoardDTO {
    private String title;

    private String content;

    private LocalDate postDate;

    private MultipartFile thumbnail;

    private EmbedMember member;
}
