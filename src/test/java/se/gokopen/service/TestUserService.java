package se.gokopen.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.UserEntity;

import static org.junit.Assert.assertNotNull;

public class TestUserService extends SpringBootTestBase {

    @Autowired
    private UserService userService;

    @Test
    public void shouldCreateUserWithAdminRights() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setRole("ROLE_ADMIN");
        user.setPassword("testuser");
        user.setEnabled(true);

        userService.saveUser(user);

        assertNotNull(user.getId());

        userService.deleteUser(user);
    }
}
