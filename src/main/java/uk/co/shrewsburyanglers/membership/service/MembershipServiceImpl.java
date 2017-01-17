package uk.co.shrewsburyanglers.membership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uk.co.shrewsburyanglers.membership.model.MemberEntity;
import uk.co.shrewsburyanglers.membership.model.MembershipException;
import uk.co.shrewsburyanglers.membership.model.PaymentEntity;
import uk.co.shrewsburyanglers.membership.model.PaymentValidator;
import uk.co.shrewsburyanglers.membership.repository.MemberRepository;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 28/09/2016
 *         Time : 13:12
 */
@Service("membershipService")
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MemberRepository memberRepository;

    public void createMember(final String firstName,
                             final String lastName,
                             final String email,
                             final String phone,
                             final String address,
                             final String postcode,
                             final Date dob,
                             final File photo) throws MembershipException {
        //create

    }

    public void updateMember(MemberEntity memberEntity) {
        memberRepository.saveAndFlush(memberEntity);
    }

    public MemberEntity getMemberEntity(final int membershipId) {
        return memberRepository.findOne(membershipId);
    }

    public List<MemberEntity> getAllMemberEntities() {
        return memberRepository.findAll();
    }


    public void createMemberWithPayment(final String firstName,
                                        final String lastName,
                                        final String email,
                                        final String phone,
                                        final String address,
                                        final String postcode,
                                        final Date dob,
                                        final File photo,
                                        final BigDecimal amount,
                                        final Date expireDate,
                                        final String transId,
                                        final String pos) throws MembershipException {

    }

    public void updateMemberWithPayment(final int memberId,
                                        final BigDecimal amount,
                                        final Date expireDate,
                                        final String transId,
                                        final String pos) throws MembershipException {

    }

    public void createMemberWithPayment(MemberEntity memberEntity) throws MembershipException {
        memberRepository.saveAndFlush(memberEntity);
    }

    /**
     * <p>
     *
     * </p>
     * @param membershipId
     * @return
     */
    public boolean isMemberValid(final int membershipId) throws MembershipException {
        MemberEntity member = memberRepository.findOne(membershipId);

        if(member == null) throw new MembershipException("No member with id - "+membershipId);

        if(!member.isMemberActive()) return false;

        List<PaymentEntity> payments = member.getPayments();
        CollectionUtils.filter(payments, new PaymentValidator());
        return !payments.isEmpty();
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberEntity> pageSearchMembers(final String queryValue) {
        return memberRepository.searchMembersByName(queryValue);
    }

    @Override
    public Page<MemberEntity> pageAllMembers(final int pageNumber, final int numPerPage) {
        PageRequest request =
                new PageRequest(pageNumber - 1, numPerPage, Sort.Direction.DESC, "lastName");
        return memberRepository.findAll(request);
    }
}
