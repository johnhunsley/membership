package uk.co.shrewsburyanglers.membership.model;

import org.apache.commons.collections.Predicate;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * <p>
 *     A {@link Predicate} for evaluating a payment is valid, i.e. today's date falls between the start and end dates
 * </p>
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 30/09/2016
 *         Time : 09:40
 */
public class PaymentValidator implements Predicate {

    /**
     * <p>
     *     Does today's date fall between the start and finish dates of the given object,
     *     inclusive of the start and end dates?
     *  </p>
     * @param o
     * @return
     */
    public boolean evaluate(Object o) {
        PaymentEntity paymentEntity = (PaymentEntity)o;
        Date today = DateTime.now(DateTimeZone.UTC).toDate();
        return !paymentEntity.getStartDate().after(today) && !paymentEntity.getExpireDate().before(today);
    }
}
