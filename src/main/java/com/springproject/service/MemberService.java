package com.springproject.service;


import com.springproject.DTO.LoginDTO;
import com.springproject.domain.Members;
import com.springproject.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MembersRepository memberrepository;

    @Transactional
    public void register(Members member){
        memberrepository.save(member);
    }

    public Members login(LoginDTO loginMember){
        return memberrepository.findByUserId(loginMember.getUserid());
    }


}
