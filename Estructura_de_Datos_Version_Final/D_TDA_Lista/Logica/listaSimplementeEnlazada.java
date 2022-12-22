package Logica;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import Interfaces.Position;
import Interfaces.PositionList;

public class listaSimplementeEnlazada<E> implements PositionList<E>{

	//Atributos de instancia
	protected Nodo<E> head;
	protected int size;
	
	//Constructor
	public listaSimplementeEnlazada() {
		head = null;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public Position<E> first() throws EmptyListException {
		return head;
	}
	
	@Override
	public Position<E> last() throws EmptyListException {
		return null;
	}
	
	@Override
	public Position<E> prev(Position<E> p) throws BoundaryViolationException, InvalidPositionException {
		return null;
	}
	
	@Override
	public Position<E> next(Position<E> p) throws BoundaryViolationException, InvalidPositionException {
		return null;
	}
	
	@Override
	public E set(Position<E> p, E e) throws InvalidPositionException {
		return null;
	}
	
	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		return null;
	}
	
	@Override
	public void addFirst(E e) {
	}
	
	@Override
	public void addLast(E e) {
	}
	
	@Override
	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
	}
	
	@Override
	public void addAfter(Position<E> p) throws InvalidPositionException {
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
}
