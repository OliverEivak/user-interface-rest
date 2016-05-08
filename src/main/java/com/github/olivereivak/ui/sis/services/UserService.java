package com.github.olivereivak.ui.sis.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.UserDAO;
import com.github.olivereivak.ui.sis.entity.Role;
import com.github.olivereivak.ui.sis.entity.User;

import lombok.extern.slf4j.Slf4j;

@Singleton
public class UserService {

    @Inject
    private UserDAO userDAO;

    public User create(User user) {
        user.setRole(Role.STUDENT);
        return userDAO.update(user);
    }
}
