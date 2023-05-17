package com.springproject.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDTO {
    private String profileimage;
    private String name;
    private String password;
    private String password2;
    private String githublink;
    private String email;
}
