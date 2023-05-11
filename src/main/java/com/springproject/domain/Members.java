package com.springproject.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter @Setter
public class Members {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String githubLink;

    @OneToMany(mappedBy = "member")
    private List<Board> boards;

    @OneToMany(mappedBy = "member")
    private List<Comments> comments;
}
