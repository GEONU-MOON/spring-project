package com.springproject.repository;

import com.springproject.domain.EmbedMember;
import com.springproject.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


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

    public EmbedMember setBoardMember(String userid){
        Members member = findByID(userid);

        EmbedMember embedMember = new EmbedMember();
        embedMember.setId(member.getId());
        embedMember.setName(member.getName());
        embedMember.setUserid(member.getUserid());
        embedMember.setEmail(member.getEmail());
        embedMember.setPassword(member.getPassword());
        embedMember.setGithubLink(member.getGithubLink());

        return embedMember;
    }

    public Optional<Members> findById(Long id) {
        return Optional.ofNullable(em.find(Members.class, id));
    }

    public List<Members> findByUserID(String userid) {
        return em.createQuery("select m from Members m where m.userid = :userid", Members.class).setParameter("userid", userid).getResultList();
    }
}
