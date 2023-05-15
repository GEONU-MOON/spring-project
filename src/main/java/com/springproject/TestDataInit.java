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

        memberRepository.save(member);

        Members savedmember = memberRepository.findByID("test");
        Board board = new Board();
        EmbedMember embedMember = memberRepository.setBoardMember(savedmember.getUserid());

        board.setTitle("테스트 데이터 제목");
        board.setContent("테스트 데이터 컨텐트");
        board.setMember(embedMember);
        board.setThumbnail("assets/images/blog/blog-post-thumb-1.jpg");
        board.setPostDate(LocalDate.now());

        Board board2 = new Board();
        board2.setTitle("테스트 데이터 제목2");
        board2.setContent("테스트 데이터 컨텐트2");
        board2.setMember(embedMember);
        board2.setThumbnail("assets/images/blog/blog-post-thumb-5.jpg");
        board2.setPostDate(LocalDate.now());

        boardRepository.save(board);
        boardRepository.save(board2);
    }

}
