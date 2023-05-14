package com.springproject.repository;

import com.springproject.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class MembersRepository {

    @Autowired
    private final EntityManager em;

    @Transactional
    public void save(Members member){
            em.persist(member);
        }

    @Transactional
    public void remove(Members member) {
        em.remove(em.contains(member) ? member : em.merge(member));
    }

    @Transactional
    public void update(Members member) {
        em.merge(member);
    }

    public Members findByID(String userid){
        return em.createQuery("select m from Members m where m.userid=:userid", Members.class).setParameter("userid", userid).getSingleResult();
    }
}
