package com.github.olivereivak.ui.sis.resource;

import static org.assertj.core.api.StrictAssertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.InternalServerErrorException;

import org.junit.Test;

import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.test.FullWebStackTestBase;

public class RegistrationResourceFWSTest extends FullWebStackTestBase<IRegistrationResource> {

    @Test
    public void register() throws Exception {
        String username = "totally-new-user";
        String password = "abc";
        String firstName = "totally";
        String lastName = "newUser";

        IRegistrationResource client = getClient(IRegistrationResource.class);

        IRegistrationResource.RegistrationForm registrationForm = new IRegistrationResource.RegistrationForm(username, password, firstName, lastName);

        Authentication authentication = client.register(registrationForm);

        assertNotNull(authentication.getToken());
        assertNotNull(authentication.getId());
        assertNotNull(authentication.getUser());
        assertEquals(firstName, authentication.getUser().getFirstName());
        assertEquals(lastName, authentication.getUser().getLastName());
        assertEquals(username, authentication.getUser().getUsername());
    }

    @Test
    public void registerExistingUsername() throws Exception {
        String username = "student";
        String password = "bla";
        String firstName = "sss";
        String lastName = "lll";

        IRegistrationResource client = getClient(IRegistrationResource.class);

        IRegistrationResource.RegistrationForm registrationForm = new IRegistrationResource.RegistrationForm(username, password, firstName, lastName);

        try {
            client.register(registrationForm);
            fail("Exception expected");
        } catch (InternalServerErrorException e) {
        }
    }

}
