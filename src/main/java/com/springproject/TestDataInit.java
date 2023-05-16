package com.springproject;

import com.springproject.domain.Board;
import com.springproject.domain.EmbedMember;
import com.springproject.domain.Members;
import com.springproject.repository.BoardRepository;
import com.springproject.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.lang.reflect.Member;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final MembersRepository memberRepository;
    private final BoardRepository boardRepository;
    @PostConstruct
    @Transactional
    public void init(){

        Members member = new Members();
        member.setEmail("test@naver.com");
        member.setGithubLink("https://github.com/rilac");
        member.setUserid("test");
        member.setPassword("test");
        member.setName("TESTDATA");
        member.setImage("assets/images/profile.png");
        member.setIntroduce("테스트 데이터입니다.");

        Members moon = new Members();
        moon.setName("문건우");
        moon.setUserid("test2");
        moon.setPassword("test");
        moon.setEmail("moondy2209@naver.com");
        moon.setGithubLink("https://github.com/GEONU-MOON");
        moon.setImage("assets/images/mu175.jpg");
        moon.setIntroduce("문건우입니다.");

        Members yeon = new Members();
        yeon.setImage("assets/image/rabbit.jpg");
        yeon.setName("임대연");
        yeon.setUserid("test3");
        yeon.setPassword("test");
        yeon.setEmail("eodos6480@gmail.com");
        yeon.setGithubLink("https://github.com/rilac");
        yeon.setIntroduce("임대연입니다.");

        memberRepository.save(member);
        memberRepository.save(moon);
        memberRepository.save(yeon);

        Members savedTest = memberRepository.findByID("test");
        Members savedMoon = memberRepository.findByID("test2");
        Members savedYeon = memberRepository.findByID("test3");

        EmbedMember embedTest = memberRepository.setBoardMember(savedTest.getUserid());
        EmbedMember embedMoon = memberRepository.setBoardMember(savedMoon.getUserid());
        EmbedMember embedYeon = memberRepository.setBoardMember(savedYeon.getUserid());

        for(int i=1; i<12; i++){
            Board board = new Board();
            board.setTitle("테스트 데이터 제목" + i);
            board.setContent("테스트 데이터 내용" + i);
            board.setMember(embedTest);
            board.setThumbnail("assets/images/blog/blog-post-thumb-" + i + ".jpg");
            board.setPostDate(LocalDate.now());
            boardRepository.save(board);
        }

        for(int i=1; i<12; i++){
            Board board = new Board();
            board.setTitle("문건우 데이터 제목" + i);
            board.setContent("문건우 데이터 내용" + i);
            board.setMember(embedMoon);
            board.setThumbnail("assets/images/blog/blog-post-thumb-" + i + ".jpg");
            board.setPostDate(LocalDate.now());
            boardRepository.save(board);
        }

        for(int i=1; i<12; i++){
            Board board = new Board();
            board.setTitle("임대연 데이터 제목" + i);
            board.setContent("임대연 데이터 내용" + i);
            board.setMember(embedYeon);
            board.setThumbnail("assets/images/blog/blog-post-thumb-" + i + ".jpg");
            board.setPostDate(LocalDate.now());
            boardRepository.save(board);
        }
    }

}
