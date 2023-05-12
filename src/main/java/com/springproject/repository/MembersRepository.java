package com.springproject.repository;

import com.springproject.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class MembersRepository {

    @Autowired
    private final EntityManager em;


    public void save(Members member){

        if( member.getId() == null){
            em.persist(member);
        }else{
            System.out.println("이미 존재하는 멤버입니다.");
            return;
        }
    }

    public Members findByUserId(String userid){
        return em.createQuery("select m from Members m where m.userid=:userid", Members.class).getSingleResult();
    }
}
