package Logica;

import Interfaces.Position;

public class NodoLista<E> implements Position<E> {
	
	private E elemento;
	private Nodo<E> siguiente;
	
	public Nodo(E item, Nodo<E> sig) {
		elemento= item;
		siguiente = sig;
	}
	
	public Nodo(E item) {
		this(item,null);
	}
	
	//SETTERS
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	//GETTERS
	public E getElemento() {
		return elemento;
	}
	
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
	
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return elemento;
	}
}
