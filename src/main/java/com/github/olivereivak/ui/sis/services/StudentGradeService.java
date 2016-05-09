package com.github.olivereivak.ui.sis.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.StudentGradeDAO;
import com.github.olivereivak.ui.sis.entity.Role;
import com.github.olivereivak.ui.sis.entity.StudentGrade;
import com.github.olivereivak.ui.sis.entity.User;

@Singleton
public class StudentGradeService {

    @Inject
    private StudentGradeDAO studentGradeDAO;

    public List<StudentGrade> getAll(User loggedInUser) {
        if (!loggedInUser.getRole().equals(Role.TEACHER)) {
            throw new RuntimeException("Not allowed to view all student's grades");
        }
        return studentGradeDAO.findAll();
    }

    public List<StudentGrade> getByUser(User user, User loggedInUser) {
        if (!loggedInUser.getRole().equals(Role.TEACHER) && !loggedInUser.getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed to view other student's grades");
        }
        return studentGradeDAO.findByUser(user);
    }
}
