package com.github.olivereivak.ui.sis.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.github.olivereivak.ui.sis.entity.Role;
import com.github.olivereivak.ui.sis.entity.User;

public class UserDAO extends BaseDAO<User> {

    public User findByUsername(String username) {
        TypedQuery<User> typedQuery = em().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        typedQuery.setParameter("username", username);

        return getSingleResult(typedQuery);
    }

    public User findByID(Long id) {
        TypedQuery<User> typedQuery = em().createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        typedQuery.setParameter("id", id);

        return getSingleResult(typedQuery);
    }

    public List<User> findAllStudents() {
        TypedQuery<User> typedQuery = em().createQuery("SELECT u FROM User u WHERE u.role = :role", User.class);
        typedQuery.setParameter("role", Role.STUDENT);

        return typedQuery.getResultList();
    }

    public int findMaxGroupID() {
        TypedQuery<User> typedQuery = em().createQuery("SELECT u FROM User u ORDER BY u.studentGroup DESC", User.class);
        typedQuery.setMaxResults(1);

        int maxGroupID = 0;
        User user = getSingleResult(typedQuery);
        if (user != null) {
            maxGroupID = user.getStudentGroup();
        }

        return maxGroupID;
    }

}
