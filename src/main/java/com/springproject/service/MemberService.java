package com.springproject.service;


import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.DTO.UpdateDTO;
import com.springproject.domain.Members;
import com.springproject.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MembersRepository memberrepository;

    @Transactional
    public void updateMember(Members member, UpdateDTO form) {
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        String newPassword = form.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            member.setPassword(newPassword);
        }
        member.setGithubLink(form.getGithublink());
        memberrepository.update(member);
    }

    @Transactional
    public void deleteMember(Members member) {
        memberrepository.remove(member);
    }

    @Transactional
    public void register(Members member){
        memberrepository.save(member);
    }

    public Members login(String id, String pw){
        Members requestMember = memberrepository.findByID(id);

        if(requestMember == null){
            return null;
        }else if(!requestMember.getPassword().equals(pw)){
            return null;
        }else{
            return requestMember;
        }
    }

    public Members findByID(String id){
        Members foundMember = memberrepository.findByID(id);

        return foundMember;
    }
}
