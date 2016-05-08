package com.github.olivereivak.ui.sis.dao;

import javax.persistence.TypedQuery;

import com.github.olivereivak.ui.sis.entity.User;

public class UserDAO extends BaseDAO<User> {

    public User findByUsername(String username) {
        TypedQuery<User> typedQuery = em().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        typedQuery.setParameter("username", username);

        return getSingleResult(typedQuery);
    }

}
