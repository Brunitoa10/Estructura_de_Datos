package Logica;

import Excepciones.InvalidPositionException;

public class DNodo<E> implements Position<E> {
	
	//Atributos
	private DNodo<E> previo, siguiente;
	private E elemento;
	
	//Constructor
	public DNodo(DNodo<E> previo, DNodo<E> siguiente, E elemento) {
		this.previo = previo;
		this.siguiente = siguiente;
		this.elemento = elemento;
	}

	public DNodo<E> getPrevio() {
		return previo;
	}

	public void setPrevio(DNodo<E> previo) {
		this.previo = previo;
	}

	public DNodo<E> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(DNodo<E> siguiente) {
		this.siguiente = siguiente;
	}

	public E getElemento() {
		return elemento;
	}

	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	public E element() throws InvalidPositionException{
		if(this.previo == null && this.siguiente == null) {
			throw new InvalidPositionException("Error: La posicion no pertenece a la lista.");
		}
		return this.elemento;
	}
}