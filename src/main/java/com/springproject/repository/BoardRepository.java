package com.springproject.repository;

import com.springproject.domain.Board;
import com.springproject.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void save(Board board){
        em.persist(board);
    }

    public Optional<Board> findById(Long id){
        return Optional.ofNullable(em.find(Board.class, id));
    }

    public List<Board> findListByID(String userid, int offset, int limit) {
        return em.createQuery("select b from Board b where b.member.userid=:userid order by b.id desc", Board.class)
                .setParameter("userid", userid)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Board> recentBoard(String userid, int limit) {
        return em.createQuery("select b from Board b where b.member.userid=:userid order by b.id desc", Board.class)
                .setParameter("userid", userid)
                .setMaxResults(limit)
                .getResultList();
    }


    public Page<Board> findPagedUserBoards(String userid, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Board> query = cb.createQuery(Board.class);
        Root<Board> root = query.from(Board.class);
        query.select(root)
                .where(cb.equal(root.get("member").get("userid"), userid))
                .orderBy(cb.desc(root.get("id")));

        TypedQuery<Board> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Board> boards = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Board> countRoot = countQuery.from(Board.class);
        countQuery.select(cb.count(countRoot))
                .where(cb.equal(countRoot.get("member").get("userid"), userid));

        Long count = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(boards, pageable, count);
    }

    public Long findPrevBoardId(String userid, Long currentId) {
        try {
            return em.createQuery("select b.id from Board b where b.member.userid=:userid and b.id < :currentId order by b.id desc", Long.class)
                    .setParameter("userid", userid)
                    .setParameter("currentId", currentId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Long findNextBoardId(String userid, Long currentId) {
        try {
            return em.createQuery("select b.id from Board b where b.member.userid=:userid and b.id > :currentId order by b.id asc", Long.class)
                    .setParameter("userid", userid)
                    .setParameter("currentId", currentId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Page<Board> findBoardsByUserid(String userid, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Board> query = cb.createQuery(Board.class);
        Root<Board> root = query.from(Board.class);

        query.select(root)
                .where(cb.equal(root.get("member").get("userid"), userid))
                .orderBy(cb.desc(root.get("id")));

        TypedQuery<Board> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Board> boards = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Board> countRoot = countQuery.from(Board.class);
        countQuery.select(cb.count(countRoot))
                .where(cb.equal(countRoot.get("member").get("userid"), userid));

        Long count = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(boards, pageable, count);
    }

}

