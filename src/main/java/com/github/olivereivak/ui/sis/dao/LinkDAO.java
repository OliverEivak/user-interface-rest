package com.github.olivereivak.ui.sis.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.github.olivereivak.ui.sis.entity.Grade;
import com.github.olivereivak.ui.sis.entity.Link;
import com.github.olivereivak.ui.sis.entity.User;

public class LinkDAO extends BaseDAO<Link> {

    public Link findByUserAndGrade(User user, Grade grade) {
        TypedQuery<Link> typedQuery = em().createQuery("SELECT l FROM Link l WHERE l.user = :user AND l.grade = :grade", Link.class);
        typedQuery.setParameter("user", user);
        typedQuery.setParameter("grade", grade);
        return getSingleResult(typedQuery);
    }

    public List<Link> findByUser(User user) {
        TypedQuery<Link> typedQuery = em().createQuery("SELECT l FROM Link l WHERE l.user = :user", Link.class);
        typedQuery.setParameter("user", user);
        return typedQuery.getResultList();
    }

    public Link findByID(Long id) {
        TypedQuery<Link> typedQuery = em().createQuery("SELECT l FROM Link l WHERE l.id = :id", Link.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }

}
