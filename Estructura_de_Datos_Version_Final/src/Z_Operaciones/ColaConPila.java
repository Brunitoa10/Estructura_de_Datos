package Z_Operaciones;

import A_Excepciones.EmptyQueueException;
import B_TDA_Pila.Stack;
import C_TDA_Cola.Queue;

//Programe una clase llamada ColaConPila que implemente la interfaz Queue utilizando una pila.

public class ColaConPila<E> implements Queue<Stack<E>>{
	
	protected Stack<E> pila;
	
	public ColaConPila() {
		pila = null;
	}
	@Override
	public int size() {
		return pila.size();
	}

	@Override
	public boolean isEmpty() {
		return pila.isEmpty();
	}

	@Override
	public Stack<E> front() throws EmptyQueueException {
		if(isEmpty()) {
			throw new EmptyQueueException("Cola vacia");
		}
		return pila;
	}

	@Override
	public void enqueue(Stack<E> element) {
		
	}

	@Override
	public Stack<E> dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		return null;
	}

}
