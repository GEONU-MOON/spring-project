package com.springproject.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.List;


@Entity
@Getter @Setter
public class Members {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "member_id", nullable = false)
    private String userid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    private String githubLink;

    private String image;

    private String introduce;

    @OneToMany(mappedBy = "member")
    private List<Comments> comments;
}
