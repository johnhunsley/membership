package uk.co.shrewsburyanglers.membership;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.shrewsburyanglers.membership.model.MemberEntity;
import uk.co.shrewsburyanglers.membership.model.MemberStatus;
import uk.co.shrewsburyanglers.membership.model.PaymentEntity;
import uk.co.shrewsburyanglers.membership.repository.MemberRepository;

import java.math.BigDecimal;
import java.sql.Date;


/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 28/09/2016
 *         Time : 10:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MembersRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testCreateMember() {
        MemberEntity member = new MemberEntity("John", "Hunsley", "10 Longden Avenue, Shrewsbury", "SY3 7RL",
                                                "07538178963", Date.valueOf("1975-09-23"), "jphunsley@gmail.com",
                                                MemberStatus.ACTIVE, "http://aws.amazon.s3.com/bucket/ID/picture");
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(new BigDecimal(25.00));
        payment.setExpireDate(Date.valueOf("2017-09-27"));
        payment.setStartDate(Date.valueOf("2016-09-27"));
        payment.setPointOfSale("Online");
        payment.setExTransactionId("1234567890abc");
        payment.setPurchaseDate(Date.valueOf("2016-09-26"));
        member.addPayment(payment);
        memberRepository.saveAndFlush(member);
    }

}
