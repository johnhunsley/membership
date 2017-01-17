package uk.co.shrewsburyanglers.membership.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 28/09/2016
 *         Time : 10:12
 */
@Entity
@Table(name = "payment", schema = "", catalog = "membership")
public class PaymentEntity implements Comparable<PaymentEntity> {
    @Id
    @Column(name = "PAYMENT_ID")
    @GeneratedValue
    private int paymentId;

    @Basic
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Basic
    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;

    @Basic
    @Column(name = "START_DATE")
    private Date startDate;

    @Basic
    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    @Basic
    @Column(name = "EX_TRANSACTION_ID")
    private String exTransactionId;

    @Basic
    @Column(name = "POINT_OF_SALE")
    private String pointOfSale;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name="MEMBER_ID", nullable = false)
    private MemberEntity member;

    public PaymentEntity() {}

    /**
     *
     * @param amount
     * @param purchaseDate
     * @param expireDate
     * @param startDate
     * @param exTransactionId
     * @param pointOfSale
     */
    public PaymentEntity(final BigDecimal amount,
                         final Date purchaseDate,
                         final Date expireDate,
                         final Date startDate,
                         final String exTransactionId,
                         final String pointOfSale) {
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.expireDate = expireDate;
        this.startDate = startDate;
        this.exTransactionId = exTransactionId;
        this.pointOfSale = pointOfSale;
    }
    /**
     *
     * @param amount
     * @param purchaseDate
     * @param expireDate
     * @param exTransactionId
     * @param pointOfSale
     */
    public PaymentEntity(final BigDecimal amount,
                         final Date purchaseDate,
                         final Date expireDate,
                         final String exTransactionId,
                         final String pointOfSale) {
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.expireDate = expireDate;
        this.startDate = new Date(DateTime.now(DateTimeZone.UTC).toDate().getTime());
        this.exTransactionId = exTransactionId;
        this.pointOfSale = pointOfSale;
    }


    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }


    public String getExTransactionId() {
        return exTransactionId;
    }

    public void setExTransactionId(String exTransactionId) {
        this.exTransactionId = exTransactionId;
    }


    public String getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(String pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (paymentId != that.paymentId) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (purchaseDate != null ? !purchaseDate.equals(that.purchaseDate) : that.purchaseDate != null) return false;
        if (expireDate != null ? !expireDate.equals(that.expireDate) : that.expireDate != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (exTransactionId != null ? !exTransactionId.equals(that.exTransactionId) : that.exTransactionId != null)
            return false;
        if (pointOfSale != null ? !pointOfSale.equals(that.pointOfSale) : that.pointOfSale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (exTransactionId != null ? exTransactionId.hashCode() : 0);
        result = 31 * result + (pointOfSale != null ? pointOfSale.hashCode() : 0);
        return result;
    }

    /**
     * <p>
     *     Compare by expiry date
     * </p>
     * @param that
     * @return
     */
    public int compareTo(PaymentEntity that) {
        return that.getExpireDate().compareTo(this.expireDate);
    }
}
