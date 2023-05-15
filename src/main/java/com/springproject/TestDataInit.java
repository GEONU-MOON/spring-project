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

        EmbedMember embedMember = memberRepository.setBoardMember(savedmember.getUserid());


        for(int i=1; i<12; i++){
            Board board = new Board();
            board.setTitle("테스트 데이터 제목" + i);
            board.setContent("테스트 데이터 내용" + i);
            board.setMember(embedMember);
            board.setThumbnail("assets/images/blog/blog-post-thumb-" + i + ".jpg");
            board.setPostDate(LocalDate.now());
            boardRepository.save(board);
        }
    }

}
