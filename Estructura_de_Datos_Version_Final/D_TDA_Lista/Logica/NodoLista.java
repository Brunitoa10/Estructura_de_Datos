package Logica;

import Interfaces.Position;

public class NodoLista<E> implements Position<E> {
	
	private E elemento;
	private NodoLista<E> siguiente;
	
	public NodoLista(E item, NodoLista<E> sig) {
		elemento= item;
		siguiente = sig;
	}
	
	public NodoLista(E item) {
		this(item,null);
	}
	
	//SETTERS
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	public void setSiguiente(NodoLista<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	//GETTERS
	public E getElemento() {
		return elemento;
	}
	
	public NodoLista<E> getSiguiente(){
		return siguiente;
	}
	
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return elemento;
	}
}
