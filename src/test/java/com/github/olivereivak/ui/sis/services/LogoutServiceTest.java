package com.github.olivereivak.ui.sis.services;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.olivereivak.ui.sis.dao.AuthenticationDAO;
import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.entity.User;

@RunWith(EasyMockRunner.class)
public class LogoutServiceTest {

    @TestSubject
    private LogoutService logoutService = new LogoutService();

    @Mock
    private AuthenticationDAO authenticationDAO;

    @Test
    public void logout() throws Exception {
        Authentication authentication = new Authentication();
        User user = new User();
        user.setUsername("user-who-is-logging-out");
        authentication.setUser(user);

        authenticationDAO.remove(authentication);

        replay(authenticationDAO);

        logoutService.logout(authentication);

        verify(authenticationDAO);
    }

}
