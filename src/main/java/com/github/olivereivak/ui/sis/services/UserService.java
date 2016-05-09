package com.github.olivereivak.ui.sis.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.UserDAO;
import com.github.olivereivak.ui.sis.entity.Role;
import com.github.olivereivak.ui.sis.entity.User;

@Singleton
public class UserService {

    @Inject
    private UserDAO userDAO;

    public User create(User user) {
        user.setRole(Role.STUDENT);
        return userDAO.update(user);
    }

    public List<User> getStudents() {
        return userDAO.findAllStudents();
    }

    public User getByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
