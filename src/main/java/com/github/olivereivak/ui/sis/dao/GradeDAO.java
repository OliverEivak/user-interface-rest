package com.github.olivereivak.ui.sis.dao;

import javax.persistence.TypedQuery;

import com.github.olivereivak.ui.sis.entity.Grade;

public class GradeDAO extends BaseDAO<Grade> {

    public Grade findByID(Long id) {
        TypedQuery<Grade> typedQuery = em().createQuery("SELECT g FROM Grade g WHERE g.id = :id", Grade.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }

}
