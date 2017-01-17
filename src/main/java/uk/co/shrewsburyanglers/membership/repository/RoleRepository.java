package uk.co.shrewsburyanglers.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.shrewsburyanglers.membership.model.RoleEntity;


/**
 *
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 27/09/2016
 *         Time : 09:04
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}
