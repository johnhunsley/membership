package uk.co.shrewsburyanglers.membership.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.co.shrewsburyanglers.membership.model.UserEntity;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 22/09/2016
 *         Time : 16:26
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select distinct u from UserEntity u where u.username = :username")
    UserEntity getUserByUsername(@Param("username") final String username);

}
