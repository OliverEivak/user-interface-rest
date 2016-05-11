package com.github.olivereivak.ui.sis.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.StudentGradeDAO;
import com.github.olivereivak.ui.sis.entity.Grade;
import com.github.olivereivak.ui.sis.entity.Role;
import com.github.olivereivak.ui.sis.entity.StudentGrade;
import com.github.olivereivak.ui.sis.entity.User;

@Singleton
public class StudentGradeService {

    @Inject
    private StudentGradeDAO studentGradeDAO;

    @Inject
    private GradeService gradeService;

    @Inject
    private UserService userService;

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

    public List<StudentGrade> createOrUpdate(List<StudentGrade> studentGrades, User loggedInUser) {
        if (!loggedInUser.getRole().equals(Role.TEACHER)) {
            throw new RuntimeException("Not allowed to create or update grades");
        }

        List<StudentGrade> results = new ArrayList<>();
        for (StudentGrade studentGrade : studentGrades) {
            User user = userService.getByID(studentGrade.getUser().getId());
            Grade grade = gradeService.getByID(studentGrade.getGrade().getId());

            // replace original - front end does not have to care about student grade ids
            StudentGrade originalStudentGrade = studentGradeDAO.findByUserAndGrade(user, grade);
            if (originalStudentGrade != null) {
                studentGrade.setId(originalStudentGrade.getId());
            }

            studentGrade.setUser(user);
            studentGrade.setGrade(grade);
            results.add(studentGradeDAO.update(studentGrade));
        }
        return results;
    }
}
