package com.springproject;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import com.springproject.repository.BoardRepository;
import com.springproject.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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
    }
//    @PostConstruct
//    @Transactional
//    public void init2(){
//        Members member = memberRepository.findByID("test");
//        Board board = new Board();
//        board.setTitle("테스트 데이터 제목");
//        board.setContent("테스트 데이터 컨텐트");
//        board.setMember(member);
//        board.setThumbnail("썸네일 경로1");
//
//        Board board2 = new Board();
//        board.setTitle("테스트 데이터 제목2");
//        board.setContent("테스트 데이터 컨텐트2");
//        board.setMember(member);
//        board.setThumbnail("썸네일 경로2");
//
//        boardRepository.save(board);
//        boardRepository.save(board2);
//    }

}
