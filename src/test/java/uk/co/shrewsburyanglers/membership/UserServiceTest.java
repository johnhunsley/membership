package uk.co.shrewsburyanglers.membership;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.shrewsburyanglers.membership.model.UserEntity;
import uk.co.shrewsburyanglers.membership.model.UserException;
import uk.co.shrewsburyanglers.membership.service.UserService;

import java.util.List;

import static junit.framework.TestCase.fail;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 27/09/2016
 *         Time : 12:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testCreateUser() {
//        final String username = "tomperring"; //password01
//        final String username = "leemcgowan"; //password02
        final String username = "chriswood";

        try {
            if(!userService.usernameExists(username)) {
                userService.createUser(username, "password03", "jphunsley@gmail.com", new Integer[]{1});
            } else System.out.println("username "+username+" already exists, skipping create user test...");
        } catch (UserException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetAllUsers() {
        List<UserEntity> users = userService.getAllUsers();

        for(UserEntity user : users) {
            System.out.println(user.toString());
        }
    }
}
