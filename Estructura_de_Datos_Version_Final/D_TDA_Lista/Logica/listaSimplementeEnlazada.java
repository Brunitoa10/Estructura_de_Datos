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
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException ("Error: Lista vacia.")
		}
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
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
