package uk.co.shrewsburyanglers.membership.model;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 30/09/2016
 *         Time : 11:01
 */
public class MembershipException extends Exception {

    public MembershipException() {
        super();
    }

    public MembershipException(String message) {
        super(message);
    }

    public MembershipException(String message, Throwable cause) {
        super(message, cause);
    }

    public MembershipException(Throwable cause) {
        super(cause);
    }

    protected MembershipException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
