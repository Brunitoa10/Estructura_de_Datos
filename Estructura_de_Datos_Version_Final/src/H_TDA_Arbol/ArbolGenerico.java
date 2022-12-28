package H_TDA_Arbol;

import java.util.Iterator;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyTreeException;
import A_Excepciones.InvalidOperationException;
import A_Excepciones.InvalidPositionException;
import D_TDA_Lista.Position;

public class ArbolGenerico<E> implements Tree<E>{
	
	//Atributos de instancia
	protected TNodo<E> raiz;
	protected int size;
	
	//Constructor
	public ArbolGenerico() {
		raiz = null;
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
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size == 0) {
			throw new EmptyTreeException("Error (root()) --> Arbol vacio.");
		}
		return this.raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> nodo = checkPosition(v);
		if(nodo == raiz) {
			throw new BoundaryViolationException("p corresponde a la raiz del árbol");
		}
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getPadre().equals(raiz);
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(size != 0) {
			throw new InvalidOperationException("Error (creatRoot(e)) --> Ya existe una raiz.");
		}
		raiz = new TNodo<E>(e);
		size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		nodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		nodo.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		
		return null;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		
		return null;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		
	}
	
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if(p == null) {
				throw new InvalidPositionException("Error (checkPosition(p)) --> Posicion nula.");
			}
			if(p.element() == null) {
				throw new InvalidPositionException("Error (checkPosition(p)) --> La posicion pasada como parametro fue eliminada previamente");
			}
		}catch(ClassCastException e) {
			throw new InvalidPositionException("Error (checkPosition(p)) --> La posicion pasada como parametro no es un nodo de la lista");
		}
		return (TNodo<E>) p;
	}
}
