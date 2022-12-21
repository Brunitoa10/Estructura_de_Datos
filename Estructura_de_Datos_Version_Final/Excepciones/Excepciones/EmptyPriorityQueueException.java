package Excepciones;

public class EmptyPriorityQueueException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}
}
