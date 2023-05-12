package com.springproject;

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

        /*
        Members member = new Members();
        member.setEmail("test@naver.com");
        member.setGithubLink("https://github.com/rilac/SpringProject");
        member.setUserid("test");
        member.setPassword("test");
        member.setName("TESTDATA");

        memberRepository.save(member);
        */
    }
}
