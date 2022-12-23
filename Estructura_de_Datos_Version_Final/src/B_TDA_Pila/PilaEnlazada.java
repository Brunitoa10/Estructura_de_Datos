package B_TDA_Pila;

import Excepciones.EmptyStackException;

public class PilaEnlazada<E> implements Stack<E>{
	
	// Atributos de instancia
	
	protected Nodo<E> head;
	protected int tamanio;
	
	//Constructor
	
	public PilaEnlazada(){
		head = null;
		tamanio = 0;
	}
	
	//Comandos
	
	public void push(E elem) {
		Nodo<E> aux = new Nodo<E>(elem,head);
		head = aux;
		tamanio++;
	} // T(n) --> O(1)
	
	//Consultas
	
	public boolean isEmpty() {
		return head == null;
	}//T(n) --> O(1)
	
	public E pop() throws EmptyStackException{
		E aux = null;
		E salida = null;
		if (isEmpty()) {
			throw new EmptyStackException("PilaEnlazada Vacia.");
		}else {
			aux = head.getElem();
			head = head.getSig();
			tamanio = tamanio-1;
			salida = aux;
		}
		return salida;
	} // T(n) --> O(1)

	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public E top() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("PilaEnlazada Vacia.");
		}
	return head.getElem();
	}
}
