package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.UserEntity;
import se.gokopen.persistence.exception.UserNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UserServiceImplTest extends SpringBootTestBase {

    @Autowired
    private UserService userService;


    @Test(expected = UserNotFoundException.class)
    public void testGetUserByIdNotFound() throws UserNotFoundException {
        userService.getUserById(123);
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword("password");
        userService.saveUser(user);

        Integer userId = user.getId();

        // Get By id
        UserEntity userLoaded = userService.getUserById(userId);
        assertThat(userLoaded.getUsername(), is("username"));

        userService.deleteUserById(userId);
    }


    @Test
    public void testGetAllUsers() throws UserNotFoundException {
        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword("password");
        userService.saveUser(user);
        UserEntity user2 = new UserEntity();
        user2.setUsername("username");
        user2.setPassword("password");
        userService.saveUser(user2);

        Integer user2Id = user2.getId();

        // test
        List<UserEntity> users = userService.getAllUsers();

        // Assert
        assertThat(users.size(), is(2));

        // Delete both
        userService.deleteUser(user);
        userService.deleteUserById(user2Id);

        // Verify deleted
        List<UserEntity> usersLeft = userService.getAllUsers();

        // Assert
        assertThat(usersLeft.size(), is(0));
    }

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
