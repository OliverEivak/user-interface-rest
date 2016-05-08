package com.github.olivereivak.ui.sis.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.olivereivak.ui.sis.entity.Authentication;
import com.github.olivereivak.ui.sis.test.TestBase;

public class LoginResourceTest extends TestBase {

	@Test
	public void loginStudent() throws Exception {
		String username = "student";
		String password = "asd";
		testLogin(username, password);
	}

	@Test
	public void loginTeacher() throws Exception {
		String username = "teacher";
		String password = "asdasd";
		testLogin(username, password);
	}

	private void testLogin(String username, String password) {
		ILoginResource.LoginForm loginForm = new ILoginResource.LoginForm(username, password);

		Authentication authentication = instance(LoginResource.class).login(loginForm);
		assertNotNull(authentication.getToken());
		assertEquals(username, authentication.getUser().getUsername());
	}

	@Test
	public void loginWrongPassword() throws Exception {
		String username = "teacher";
		String password = "wrong-pw";

		ILoginResource.LoginForm loginForm = new ILoginResource.LoginForm(username, password);

		Authentication authentication = instance(LoginResource.class).login(loginForm);
		assertNull(authentication);
	}

}
