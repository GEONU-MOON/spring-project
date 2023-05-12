package com.springproject.domain;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SpringMain {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("spring");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
            try {
                tx.commit();
            } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            em.clear();
        }

    }
}
