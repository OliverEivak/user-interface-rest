package com.github.olivereivak.ui.sis.services;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.olivereivak.ui.sis.dao.AuthenticationDAO;
import com.github.olivereivak.ui.sis.dao.UserDAO;
import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.entity.User;

@RunWith(EasyMockRunner.class)
public class LoginServiceTest {

    @TestSubject
    private LoginService loginService = new LoginService();

    @Mock
    private UserDAO userDAO;

    @Mock
    private AuthenticationDAO authenticationDAO;

    @Test
    public void login() {
        String username = "some-user";
        String password = "some-pw";

        User user = new User();
        user.setUsername(username);
        user.setPassword("$2a$10$IiZjyNNGL6XLR/T3HN5KOO3ZP7yFnhj3E8E.9F6pm7/FlBjd1Vf8G".getBytes()); // some-pw
        expect(userDAO.findByUsername(username)).andReturn(user);

        Capture<Authentication> capturedAuthentication = EasyMock.newCapture();
        expectUpdate(capturedAuthentication);

        replay(userDAO, authenticationDAO);

        Authentication authentication = loginService.login(username, password);

        verify(userDAO, authenticationDAO);

        assertSame(capturedAuthentication.getValue(), authentication);
        assertEquals(username, authentication.getUser().getUsername());
    }

    @Test
    public void loginWrongPassword() {
        String username = "some-user";
        String password = "wrong-pw";

        User user = new User();
        user.setUsername(username);
        user.setPassword("$2a$10$IiZjyNNGL6XLR/T3HN5KOO3ZP7yFnhj3E8E.9F6pm7/FlBjd1Vf8G".getBytes()); // some-pw
        expect(userDAO.findByUsername(username)).andReturn(user);

        replay(userDAO, authenticationDAO);

        Authentication authentication = loginService.login(username, password);

        verify(userDAO, authenticationDAO);

        assertNull(authentication);
    }

    private void expectUpdate(Capture<Authentication> capturedAuthentication) {
        expect(authenticationDAO.update(EasyMock.capture(capturedAuthentication)))
                .andAnswer(capturedAuthentication::getValue);
    }

}
