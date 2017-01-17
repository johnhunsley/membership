package uk.co.shrewsburyanglers.membership;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.shrewsburyanglers.membership.model.Hash;
import uk.co.shrewsburyanglers.membership.model.RoleEntity;
import uk.co.shrewsburyanglers.membership.model.UserEntity;
import uk.co.shrewsburyanglers.membership.repository.RoleRepository;
import uk.co.shrewsburyanglers.membership.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date :
 *         22/09/2016
 *         Time : 16:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testGetUsers() {
        List<UserEntity> users = userRepository.findAll();

        for(UserEntity user : users) {
            System.out.println(user.getId());
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void testCreateUser() {
        final String username = "johnhunsley";

        if(userRepository.getUserByUsername(username) != null) {
            System.out.println("test user already exists in DB, skipping write test");

        } else {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPasswordHash(new Hash(Hash.SHA1_TYPE).hash("snowball33"));
            user.setEmail("jphunsley@gmail.com");
            List<RoleEntity> allRoles = roleRepository.findAll();
            Set<RoleEntity> associatedRoles = new HashSet<>();
            associatedRoles.addAll(allRoles);
            user.setRoles(associatedRoles);
            userRepository.saveAndFlush(user);
        }
    }
}
