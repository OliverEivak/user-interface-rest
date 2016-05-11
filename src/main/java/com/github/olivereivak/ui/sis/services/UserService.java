package com.github.olivereivak.ui.sis.services;

import java.util.ArrayList;
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

    private static final Object lock = new Object();

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

    public User getByID(Long id) {
        return userDAO.findByID(id);
    }

    /**
     * Only student group can be updated.
     */
    public User update(User user) {
        User originalUser = getByUsername(user.getUsername());
        originalUser.setStudentGroup(user.getStudentGroup());
        return userDAO.update(originalUser);
    }

    public List<User> join(List<User> students) {
        synchronized (lock) {
            List<User> results = new ArrayList<>();
            int nextGroupID = userDAO.findMaxGroupID() + 1;
            for (User student : students) {
                student.setStudentGroup(nextGroupID);
                results.add(update(student));
            }
            return results;
        }
    }

    public List<User> split(List<User> students) {
        synchronized (lock) {
            List<User> results = new ArrayList<>();
            for (User student : students) {
                student.setStudentGroup(0);
                results.add(update(student));
            }
            return results;
        }
    }

}
