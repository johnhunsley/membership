package uk.co.shrewsburyanglers.membership;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.shrewsburyanglers.membership.model.MemberEntity;
import uk.co.shrewsburyanglers.membership.model.MemberStatus;
import uk.co.shrewsburyanglers.membership.model.PaymentEntity;
import uk.co.shrewsburyanglers.membership.repository.MemberRepository;
import uk.co.shrewsburyanglers.membership.service.MembershipServiceImpl;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 28/09/2016
 *         Time : 15:50
 */
@RunWith(MockitoJUnitRunner.class)
public class MembershipServiceTest {
    final int id = 1;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MembershipServiceImpl membershipService = new MembershipServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        //Mockito does like injecting interfaces so need to manually set it
        membershipService.setMemberRepository(memberRepository);
    }

    @Test
    public void testIsMemberValid() {

        try {
            //Ensure valid date is always one year in future from now
            Date nextYear = new Date(DateTime.now(DateTimeZone.UTC).plusYears(1).toDate().getTime());
            MemberEntity member = new MemberEntity();
            member.setId(id);
            member.setStatus(MemberStatus.ACTIVE);
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2014-09-27"), Date.valueOf("2015-10-27"), Date.valueOf("2014-09-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2016-04-27"), nextYear, Date.valueOf("2016-04-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2013-09-27"), Date.valueOf("2014-09-27"), Date.valueOf("2013-09-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2012-09-27"), Date.valueOf("2013-12-31"), Date.valueOf("2012-09-27"), "12345", "online"));
            when(memberRepository.findOne(id)).thenReturn(member);
            assertTrue(memberRepository.findOne(id) != null);
            assertTrue(membershipService.isMemberValid(id));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testIsMemberInvalid() {

        try {
            MemberEntity member = new MemberEntity();
            member.setId(id);
            member.setStatus(MemberStatus.ACTIVE);
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2014-09-27"), Date.valueOf("2015-10-27"), Date.valueOf("2014-09-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2016-04-27"), Date.valueOf("2016-09-27"), Date.valueOf("2016-04-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2013-09-27"), Date.valueOf("2014-09-27"), Date.valueOf("2013-09-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2012-09-27"), Date.valueOf("2013-12-31"), Date.valueOf("2012-09-27"), "12345", "online"));
            when(memberRepository.findOne(id)).thenReturn(member);
            assertTrue(memberRepository.findOne(id) != null);
            assertFalse(membershipService.isMemberValid(id));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testIsMemberInvalidIfNotActive() {
        try {
            Date nextYear = new Date(DateTime.now(DateTimeZone.UTC).plusYears(1).toDate().getTime());
            MemberEntity member = new MemberEntity();
            member.setId(id);
            member.setStatus(MemberStatus.INACTIVE);
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2014-09-27"), Date.valueOf("2015-10-27"), Date.valueOf("2014-09-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2016-04-27"), Date.valueOf("2016-09-27"), nextYear, "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2013-09-27"), Date.valueOf("2014-09-27"), Date.valueOf("2013-09-27"), "12345", "online"));
            member.addPayment(new PaymentEntity(new BigDecimal(25.00), Date.valueOf("2012-09-27"), Date.valueOf("2013-12-31"), Date.valueOf("2012-09-27"), "12345", "online"));
            when(memberRepository.findOne(id)).thenReturn(member);
            assertTrue(memberRepository.findOne(id) != null);
            assertFalse(membershipService.isMemberValid(id));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
