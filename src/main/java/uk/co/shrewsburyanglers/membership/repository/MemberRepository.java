package uk.co.shrewsburyanglers.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.co.shrewsburyanglers.membership.model.MemberEntity;

import java.util.List;

/**
 *
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 28/09/2016
 *         Time : 10:21
 */
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    @Query("select m from MemberEntity m where m.firstName like CONCAT('%',:queryValue,'%') or m.lastName like CONCAT('%',:queryValue,'%')")
    List<MemberEntity> searchMembersByName(String queryValue);
}
