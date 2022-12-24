package D_TDA_Lista;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyListException;
import A_Excepciones.InvalidPositionException;
import E_TDA_Iterable.Iterable;
import E_TDA_Iterable.Iterator;

//Es mas eficiente que la listaSimplementeEnlazada

public class listaDoblementeEnlazada<E> implements PositionList<E>{
	//Atributos de instancia
	protected DNodo<E> head, tail;
	protected int size;
	
	//Constructor
	public listaDoblementeEnlazada() {
		head = new DNodo<E>();
		tail = new DNodo<E>();
		head.setSiguiente(tail);
		tail.setPrevio(head);
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
		if(size == 0) {
			throw new EmptyListException("Error (first()) --> Lista vacia.");
		}
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(size == 0) {
			throw new EmptyListException("Error (last()) --> Lista vacia.");
		}
		return tail;
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
