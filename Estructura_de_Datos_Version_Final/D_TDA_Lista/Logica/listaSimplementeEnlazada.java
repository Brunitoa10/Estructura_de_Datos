package Logica;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import Interfaces.Position;
import Interfaces.PositionList;

public class listaSimplementeEnlazada<E> implements PositionList<E>{

	//Atributos de instancia
	protected NodoLista<E> head;
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
		if (isEmpty())
			throw new EmptyListException("Error (First()): Lista Vacia.");
		return head;
	}


	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("Error (Last()): Lista Vacia.");
		}
		NodoLista<E> retorno = head;
		while(retorno.getSiguiente() != null) {
			retorno = retorno.getSiguiente();
		}
		return retorno;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoLista<E> nodoList = checkPosition(p);
		if(nodoList.getSiguiente() == null) {
			throw new BoundaryViolationException("No se puede pedir el siguiente al ultimo elemento");
		}
		return nodoList.getSiguiente();
	}
	private NodoLista<E> checkPosition(Position<E> p) throws InvalidPositionException{
		NodoLista<E> retorno = null;
		try {
			retorno = (NodoLista<E>) p;
		}catch(ClassCastException e) {
			throw new InvalidPositionException("Error: Posicion Invalida");
		}
		return retorno;
	}
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		checkPosition(p);
		if(p == head) {
			throw new InvalidPositionException("p es la primera posicion.");
		}
		NodoLista<E> retorno = head;
		while(retorno.getSiguiente() != p && retorno.getSiguiente() != null) {
			retorno = retorno.getSiguiente();
		}
		if(retorno.getSiguiente() == null) {
			throw new InvalidPositionException("Error: La posicion 'p' no pertenece a la lista.");
		}
		return retorno;
	}

	@Override
	public void addFirst(E element) {
		head = new NodoLista<E>(element,head);
		size++;
	}

	@Override
	public void addLast(E element) {
		if(isEmpty()) {
			addFirst(element);
		}else {
			NodoLista<E> p = head;
			while(p.getSiguiente() != null) {
				p = p.getSiguiente();
			}
			p.setSiguiente(new NodoLista<E>(element));
			size++;
		}
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		NodoLista<E> nodo = checkPosition(p);
		NodoLista<E> nuevoNodo = new NodoLista<E>(element);
		nuevoNodo.setSiguiente(nodo.getSiguiente());
		nodo.setSiguiente(nuevoNodo);
		size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		checkPosition(p);
        try {
			if( p == first()) {
				addFirst(element);
			} else {
				addAfter( prev(p), element );
			}
		} catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			e.printStackTrace();
		}
		
	}//T_addBefore(n) = O(n)

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if(isEmpty()){
			throw new InvalidPositionException("Error (Remove(p)): Lista vacia.");
		}
		NodoLista<E> nodo = checkPosition(p);
		try {
			if(p == first()) {
				head = nodo.getSiguiente();
			}else {
				checkPosition(prev(p)).setSiguiente(nodo.getSiguiente());
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		size--;
		E retorno = p.element();
		nodo.setElemento(null);
		nodo.setSiguiente(null);
		return retorno;
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
