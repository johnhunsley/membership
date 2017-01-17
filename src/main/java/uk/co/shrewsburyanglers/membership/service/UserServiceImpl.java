package uk.co.shrewsburyanglers.membership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.co.shrewsburyanglers.membership.model.*;
import uk.co.shrewsburyanglers.membership.repository.RoleRepository;
import uk.co.shrewsburyanglers.membership.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 27/09/2016
 *         Time : 12:00
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * <p>
     *
     * </p>
     * @param username
     * @param password
     * @param email
     * @param roleIds
     */
    public void createUser(String username,
                           final String password,
                           final String email,
                           final Integer[] roleIds) throws UserException {
        //set username to lowercase
        username = username.toLowerCase();
        //check username exists?
        if(userRepository.getUserByUsername(username) != null)
            throw new UserException("Username "+username+" already exists");

        UserEntity user = new UserEntity(username, new Hash(Hash.SHA1_TYPE).hash(password), email);
        user.setActive(YNEnum.Y);

        //get selected Role objects
        List<RoleEntity> roles = roleRepository.findAll(Arrays.asList(roleIds));
        Set<RoleEntity> uniqueRoles = new HashSet<RoleEntity>();
        uniqueRoles.addAll(roles);
        user.setRoles(uniqueRoles);

        //persist user
        userRepository.saveAndFlush(user);
    }

    public boolean usernameExists(final String username) {
        return userRepository.getUserByUsername(username) != null;
    }

    public void changeUserPassword(UserEntity user, final String newPassword) {
        user.setPasswordHash(new Hash(Hash.SHA1_TYPE).hash(newPassword));
        userRepository.saveAndFlush(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(UserEntity userEntity) {
        userRepository.saveAndFlush(userEntity);
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        return userRepository.getUserByUsername(s);
    }
}
