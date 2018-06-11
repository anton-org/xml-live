
package com.accenture.foundation.common.exception;

public class ExceptionMessage {

    public static final String WARN = "WARN";
    public static final String DEBUG = "DEBUG";
    public static final String ERROR = "ERROR";
    public static final String FATAL = "FATAL";
    public static final String INFO = "INFO";
    private static final String PERIOD = " . \n";

    private String displayMessage;
    private String severity;
    private transient String informationCode;
    private transient String userResolution;
    private String uniqueID;
    private transient StackTraceElement[] stack;
    private transient Throwable exceptionCause;
    private transient String exceptionMsg;
    private int code;

    /**
     * Creates a new ExceptionMessage object.
     */
    public ExceptionMessage() {
        super();
    }

    /**
     * Creates a new ExceptionMessage object.
     * 
     * @param theMessage
     *            The textual error message
     * @param theCode
     *            The error message code
     * @param theSeverity
     *            The severity of the error
     */
    public ExceptionMessage(final String informationCode,
            final String theMessage, final int theCode,
            final String theSeverity, final String theUserResolution) {
        this.displayMessage = theMessage;
        this.code = theCode;
        this.severity = theSeverity;
        this.informationCode = informationCode;
        this.userResolution = theUserResolution;
    }

    /**
     * Sets the code.
     * 
     * @param theCode
     *            The code to set
     */
    public void setCode(final int theCode) {
        this.code = theCode;
    }

    /**
     * Returns the code.
     * 
     * @return int
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the message.
     * 
     * @param aDisplayMessage
     *            The message to set
     */
    public void setDisplayMessage(final String aDisplayMessage) {
        this.displayMessage = aDisplayMessage;
    }

    /**
     * Returns the message.
     * 
     * @return String
     */
    public String getDisplayMessage() {
        return this.displayMessage;
    }

    /**
     * Sets the severity.
     * 
     * @param theSeverity
     *            The severity to set
     */
    public void setSeverity(final String theSeverity) {
        this.severity = theSeverity;
    }

    /**
     * Returns the severity.
     * 
     * @return String
     */
    public String getSeverity() {
        return this.severity;
    }

    /**
     * Returns the logicalName
     * 
     * @return String
     * 
     */
    public String getInformationCode() {
        return this.informationCode;
    }

    /**
     * Returns the userResolution.
     * 
     * @return String
     * 
     */
    public String getUserResolution() {
        return this.userResolution;
    }

    /**
     * Returns the unique id for the exception
     * 
     * @return string
     */
    public String getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Sets the unique id for the exception
     * 
     * @param uniqueID
     */
    public void setUniqueID(final String uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**
     * Gets the stack trace of the exception that occured
     * 
     * @return String
     */
    public StackTraceElement[] getExceptionStackTrace() {
        StackTraceElement[] sTraceElements = null;

        if (null != this.stack) {
            sTraceElements = this.stack.clone();
        }

        return sTraceElements;
    }

    /**
     * Sets the stack trace of the exception that occured
     * 
     * @param stack
     * 
     */
    public void setExceptionStackTrace(
            final StackTraceElement[] stackTraceElements) {
        if (null == stackTraceElements) {
            this.stack = null;
        } else {
            this.stack = stackTraceElements.clone();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unique ID: ");
        stringBuilder.append(this.uniqueID);

        stringBuilder.append(PERIOD);
        stringBuilder.append("Information Code: ");
        stringBuilder.append(this.informationCode);

        stringBuilder.append(PERIOD);
        stringBuilder.append("Error Code: ");
        stringBuilder.append(this.code);

        stringBuilder.append(PERIOD);
        stringBuilder.append("User Message: ");
        stringBuilder.append(this.displayMessage);

        stringBuilder.append(PERIOD);
        stringBuilder.append("Message: ");
        stringBuilder.append(this.exceptionMsg);

        stringBuilder.append(PERIOD);
        stringBuilder.append("Resolution: ");
        stringBuilder.append(this.userResolution);

        stringBuilder.append(PERIOD);
        stringBuilder.append("Detailed Stack Trace: ");
        this.convert(stringBuilder, this.getExceptionStackTrace());
        stringBuilder.append(PERIOD);

        stringBuilder.append(PERIOD);
        stringBuilder.append("Cause: ");
        stringBuilder.append(this.exceptionCause);

        return stringBuilder.toString();
    }

    /**
     * This Method converts the stack trace of an exception into String from
     * StackTraceElement.
     * 
     * @param buffer
     *            The buffer to which we will append.
     * @param stack
     * 
     * @return the Exception Description
     */
    private StringBuilder convert(final StringBuilder newStringBuilder,
            final StackTraceElement[] stack) {
        StringBuilder result = newStringBuilder;

        if (stack != null && stack.length > 0) {
            if (result == null) {
                result = new StringBuilder();
            }

            result.append(stack[0]);

            for (int i = 1; i < stack.length; i++) {
                result.append('\n');
                result.append(stack[i]);
            }
        } else {
            if (result == null) {
                result = new StringBuilder();
            }
        }

        return result;
    }

    /**
     * This method returns the original exception cause. A cause is another
     * throwable that caused the final exception to be thrown. The cause
     * facility was added new in release 1.4. It is also known as the chained
     * exception facility, as the cause can, itself, have a cause, and so on,
     * leading to a "chain" of exceptions, each caused by another. Please note
     * that this cause may return null if no cause was set using the @see
     * {@link ExceptionMessage.setExceptionCause()} method.
     * 
     * @return the exceptionCause
     */
    public Throwable getExceptionCause() {
        return this.exceptionCause;
    }

    /**
     * This method sets the original exception cause. A cause is another
     * throwable that caused the final exception to be thrown. The cause
     * facility was added new in release 1.4. It is also known as the chained
     * exception facility, as the cause can, itself, have a cause, and so on,
     * leading to a "chain" of exceptions, each caused by another. this is
     * OPTIONAL value and only to be used if there is a strong need to display
     * the cause of the exception.
     * 
     * @param exceptionCause
     *            the exceptionCause to set
     */
    public void setExceptionCause(final Throwable exceptionCause) {
        this.exceptionCause = exceptionCause;
    }

    /**
     * Returns the detail message string of this throwable. The detail message
     * string of this Throwable instance (which may be null).
     * 
     * @return the exceptionMessage
     */
    public String getExceptionMessage() {
        return this.exceptionMsg;
    }

    /**
     * Sets the detail message string of the throwable that the exception
     * message may use to display. this is optional and may not always be set.
     * 
     * @param exceptionMessage
     *            the exceptionMessage to set
     */
    public void setExceptionMessage(final String exceptionMessage) {
        this.exceptionMsg = exceptionMessage;
    }

}
