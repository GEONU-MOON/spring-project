package com.springproject.domain;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter @Getter
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Members member;

    private String title;

    private String content;

    private LocalDate postDate;

    private String thumbnail;
}
