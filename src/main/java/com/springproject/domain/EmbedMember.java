package com.springproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Getter @Setter
public class EmbedMember {
        private Long id;
        private String userid;
        private String name;
        private String email;
        private String password;
        private String githubLink;
}
