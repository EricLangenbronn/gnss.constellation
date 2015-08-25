package fr.gnss.constellation.ouranos.commons.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4082624330729087266L;

	public BusinessException()
	{
		super();
	}
	
	public BusinessException(String message)
	{
		super(message);
	}
	
	public BusinessException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public BusinessException(Throwable cause)
	{
		super(cause);
	}
}
