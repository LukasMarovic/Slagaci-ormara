package com.progi.progi;

import com.progi.progi.model.User;
import com.progi.progi.repository.UserRepository;
import com.progi.progi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTest {

	private User user;

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
		user = new User();
	}

	@Test
	void nullPasswordTest() {
		user.setLozinka(null);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			userService.add(user);
		});

		assertEquals("Password cannot be null", exception.getMessage());
	}

	@Test
	void emptyPasswordTest() {
		user.setLozinka("");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			userService.add(user);
		});

		assertEquals("Password cannot be empty", exception.getMessage());
	}

	@Test
	void shortPasswordTest() {
		user.setLozinka("asdf");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			userService.add(user);
		});

		assertEquals("Password must be at least 8 characters long", exception.getMessage());
	}

}
