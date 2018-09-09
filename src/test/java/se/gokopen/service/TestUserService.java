package se.gokopen.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.gokopen.Application;
import se.gokopen.persistence.entity.UserEntity;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserService {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void shouldCreateUserWithAdminRights(){
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
