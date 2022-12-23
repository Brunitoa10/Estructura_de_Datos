package A_Excepciones;

public class EmptyQueueException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Clase EmptyQueueException
	 * @author Bruno Parisi 
	 */
	public EmptyQueueException (String msg) 
	{
		super(msg);
	}
}
