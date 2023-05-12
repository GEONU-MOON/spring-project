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

    public void save(Members members) {
        em.persist(members);
    }
}
