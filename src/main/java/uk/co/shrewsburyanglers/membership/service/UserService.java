package uk.co.shrewsburyanglers.membership.service;

import uk.co.shrewsburyanglers.membership.model.UserEntity;
import uk.co.shrewsburyanglers.membership.model.UserException;

import java.util.List;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 27/09/2016
 *         Time : 11:57
 */
public interface UserService {

    /**
     * <p>
     *
     * </p>
     * @param username
     * @param pasword
     * @param email
     * @param roleIds
     */
    void createUser(String username, String pasword, String email, Integer[] roleIds) throws UserException;

    boolean usernameExists(String username);

    void changeUserPassword(UserEntity user, String newPassword);

    List<UserEntity> getAllUsers();

    void updateUser(UserEntity userEntity);

}
