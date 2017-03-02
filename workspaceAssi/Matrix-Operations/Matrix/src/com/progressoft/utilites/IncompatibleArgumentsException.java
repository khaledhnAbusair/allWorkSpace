package com.progressoft.utilites;



/**
 * @author ProgressSoft
 *
 */
public class IncompatibleArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 4578095021520647821L;

	public IncompatibleArgumentsException() {
        super();
    }

    public IncompatibleArgumentsException(String message) {
        super(message);
    }

    public IncompatibleArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleArgumentsException(Throwable cause) {
        super(cause);
    }
}
