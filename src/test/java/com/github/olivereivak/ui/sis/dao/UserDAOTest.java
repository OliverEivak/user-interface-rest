package com.github.olivereivak.ui.sis.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.github.olivereivak.ui.sis.entity.Role;
import com.github.olivereivak.ui.sis.entity.User;
import com.github.olivereivak.ui.sis.test.DAOTestBase;

public class UserDAOTest extends DAOTestBase {

    @Test
    public void find() {
        UserDAO userDAO = instance(UserDAO.class);

        User user = userDAO.findByUsername("student");
        assertEquals(Long.valueOf(1), user.getId());
        Assert.assertEquals(Role.STUDENT, user.getRole());
    }

}
