package com.github.olivereivak.ui.sis.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.github.olivereivak.ui.sis.entity.Grade;
import com.github.olivereivak.ui.sis.entity.StudentGrade;
import com.github.olivereivak.ui.sis.entity.User;

public class StudentGradeDAO extends BaseDAO<StudentGrade> {

    public List<StudentGrade> findAll() {
        TypedQuery<StudentGrade> typedQuery = em().createQuery("SELECT sg FROM StudentGrade sg", StudentGrade.class);
        return typedQuery.getResultList();
    }

    public List<StudentGrade> findByUser(User user) {
        TypedQuery<StudentGrade> typedQuery = em().createQuery("SELECT sg FROM StudentGrade sg WHERE sg.user = :user", StudentGrade.class);
        typedQuery.setParameter("user", user);
        return typedQuery.getResultList();
    }

    public StudentGrade findByUserAndGrade(User user, Grade grade) {
        TypedQuery<StudentGrade> typedQuery = em().createQuery("SELECT sg FROM StudentGrade sg WHERE sg.user = :user "
                + "AND sg.grade = :grade", StudentGrade.class);
        typedQuery.setParameter("user", user);
        typedQuery.setParameter("grade", grade);
        return getSingleResult(typedQuery);
    }

}
