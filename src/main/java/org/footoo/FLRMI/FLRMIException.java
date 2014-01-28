/**
 * 
 */
package org.footoo.FLRMI;

/**
 * FLRMI���쳣
 * 
 * @author fengjing.yfj
 *
 */
public class FLRMIException extends RuntimeException {

    /** ���к� */
    private static final long serialVersionUID = 3212769079248061516L;

    /** ��������쳣 */
    private Exception         exception;

    public FLRMIException(String message) {
        super(message);
    }

    public FLRMIException(Exception e) {
        super(e);
        this.exception = e;
    }

    /**
     * Getter method for property <tt>exception</tt>.
     * 
     * @return property value of exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Setter method for property <tt>exception</tt>.
     * 
     * @param exception value to be assigned to property exception
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

}
