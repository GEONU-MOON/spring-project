package com.springproject.repository;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    @Autowired
    private final EntityManager em;

    @Transactional
    public void save(Board board){
        em.persist(board);
    }


    public List<Board> findListByID(String userid){
        return em.createQuery("select b from Board b where b.member.userid=:userid order by b.id desc", Board.class)
                .setParameter("userid", userid).getResultList();
    }

    public List<Board> recentBoard(String userid){
        return em.createQuery("select b from Board b where b.member.userid=:userid order by b.id desc",Board.class)
                .setParameter("userid", userid).setMaxResults(3).getResultList();
    }
}
