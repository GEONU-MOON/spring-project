package com.springproject.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class RegisterDTO {
    private String userid;
    private String name;
    private String email;
    private String password;

    private String githubLink;

    private String newpassword;
    private String newpasswordck;
}
