package com.springproject.repository;

import com.springproject.domain.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    @Transactional
    public void save(Comments comment) {
        em.persist(comment);
    }

    @Transactional
    public void deleteById(Long commentId) {
       Query query = em.createQuery("delete from Comments c Where c.id = :commentId");
       query.executeUpdate();
       em.getTransaction().commit();
    }

    public List<Comments> findByBoardId(Long boardId) {
        return em.createQuery("SELECT c FROM Comments c WHERE c.board.id = :boardId", Comments.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }
}

