package com.github.olivereivak.ui.sis.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.test.DAOTestBase;

public class AuthenticationDAOTest extends DAOTestBase {

    @Test
    public void find() {
        AuthenticationDAO authenticationDAO = instance(AuthenticationDAO.class);

        Authentication foundAuthentication = authenticationDAO.findByToken("qwe");
        assertEquals("teacher", foundAuthentication.getUser().getUsername());
    }

}
