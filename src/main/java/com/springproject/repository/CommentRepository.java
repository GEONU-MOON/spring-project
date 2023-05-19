package com.springproject.repository;

import com.springproject.domain.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    @Transactional
    public void save(Comments comment) {
        em.persist(comment);
    }

    public List<Comments> findByBoardId(Long boardId) {
        return em.createQuery("SELECT c FROM Comments c WHERE c.board.id = :boardId", Comments.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long commentId) {
        Comments comment = em.find(Comments.class, commentId);
        em.remove(comment);
    }


}

