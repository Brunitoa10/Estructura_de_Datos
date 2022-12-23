package C_TDA_Cola;

import B_TDA_Pila.Nodo;
import Excepciones.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E>{
	//Equiv a ColaConPila del tp solo cambie el nombre
	//Atributos de instancia
	protected int cantElem;
	protected Nodo<E> head,tail;
	
	//Constructor
	
	public ColaEnlazada() {
		head = tail = null;
		cantElem = 0;
	}
	
	//Comandos
	public void enqueue(E elem) {
		Nodo<E> nodo = new Nodo<E>(elem);
		
		nodo.setElem(elem);
		nodo.setSig(null);
		
		if (cantElem == 0) {
			head = nodo;
		}else {
			tail.setSig(nodo);
		}
		tail = nodo;
		cantElem++;
	}

	@Override
	public int size() {
		return cantElem;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cantElem == 0;
	}

	@Override
	public E front() throws EmptyQueueException {
		E salida;
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia"); 
		}else {
			salida = head.getElem();
		}
		return salida;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		E salida,temp;
		
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia"); 
		}else {
			temp = head.getElem();
			head = head.getSig();
			cantElem--;
			if (isEmpty()) {
				tail = null;
			}
			salida = temp;
		}
		return salida;
	}
}
