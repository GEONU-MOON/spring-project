package com.springproject.repository;

import com.springproject.domain.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    @Transactional
    public void save(Comments comment) {
        em.persist(comment);
    }

}

