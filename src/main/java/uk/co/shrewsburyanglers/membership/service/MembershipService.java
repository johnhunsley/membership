package uk.co.shrewsburyanglers.membership.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uk.co.shrewsburyanglers.membership.model.MemberEntity;
import uk.co.shrewsburyanglers.membership.model.MembershipException;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 28/09/2016
 *         Time : 12:25
 */
public interface MembershipService {

    void createMember(String firstName, String lastName, String email, String phone,
                      String address, String postcode, Date dob, File photo) throws MembershipException;

    void updateMember(MemberEntity memberEntity);

    MemberEntity getMemberEntity(int membershipId);

    List<MemberEntity> getAllMemberEntities();

    void createMemberWithPayment(String firstName, String lastName, String email, String phone,
                      String address, String postcode, Date dob, File photo, BigDecimal amount, Date expireDate, String transId, String pos) throws MembershipException;

    void createMemberWithPayment(MemberEntity memberEntity) throws MembershipException;

    /**
     * <p>
     *     Is the member not banned and has a payment which has not expired before today
     * </p>
     * @param membershipId
     * @return true if the {@link MemberEntity} with the given id has a status of ACTIVE
     */
    boolean isMemberValid(int membershipId) throws MembershipException;

    List<MemberEntity> pageSearchMembers(String queryValue);

    Page<MemberEntity> pageAllMembers(int pageNumber, int numPerPage);
}
