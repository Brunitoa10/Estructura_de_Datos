package Excepciones;

public class operacionInvalidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public operacionInvalidaException (String msg) {
		super(msg);
	}
}
