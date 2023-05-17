package com.springproject.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateDTO {
    private MultipartFile profileimage;
    private String name;
    private String password;
    private String password2;
    private String githublink;
    private String email;
}
