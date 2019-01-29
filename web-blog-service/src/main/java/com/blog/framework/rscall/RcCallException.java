package com.blog.framework.rscall;

public class RcCallException extends Exception{

	private static final long serialVersionUID = -503432040188325064L;
	
	public RcCallException () {
		super();
	}
	
    public RcCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RcCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public RcCallException(String message) {
        super(message);
    }

    public RcCallException(Throwable cause) {
        super(cause);
    }

}
