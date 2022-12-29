package H_TDA_Arbol;

import java.util.Iterator;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyListException;
import A_Excepciones.EmptyTreeException;
import A_Excepciones.InvalidOperationException;
import A_Excepciones.InvalidPositionException;
import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;

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
		PositionList<E> list = new listaDoblementeEnlazada<E>();
		for(Position<E> p : positions()) {
			list.addLast(p.element());
		}
		return list.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new listaDoblementeEnlazada<Position<E>>();
		//Si el arbol no esta vacio hago un recorrido preOrden desde la raiz
		if(size != 0) {
			pre(raiz,lista);
		}
		return lista;
	}
	
	private void pre(TNodo<E> v, PositionList<Position<E>> l) {
		l.addLast(v); //La visita de v consiste en encolar v en l
		for(TNodo<E> n : v.getHijos()) {
			pre(n,l);
		}
	}
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		E retorno = nodo.element();
		nodo.setElemento(e);
		return retorno;
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
			throw new BoundaryViolationException("Error (parent(v)) --> p corresponde a la raiz del árbol");
		}
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> lista = new listaDoblementeEnlazada<Position<E>>();  
		for(TNodo<E> n : nodo.getHijos()) {
			lista.addLast(n);
		}
		return lista;
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
		return nodo.getPadre() == null; //Si nodo es la raiz getPadre retorna null
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
		TNodo<E> padre = checkPosition(p);
		if(size == 0) {
			throw new InvalidPositionException("Error (addFirstChild(p,e)) --> Arbol Vacio.");
		}
		
		TNodo<E> hijo = new TNodo<E>(e,padre);
		padre.getHijos().addFirst(hijo);
		size++;
		return hijo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if(size == 0) {
			throw new InvalidPositionException("Error (addLastChild(p,e)) --> Arbol Vacio.");
		}
		
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		nodo.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		//Si el arbol esta vacio
		if(size == 0) {
			throw new InvalidPositionException("Error (addBefore(p,rb,e)) --> Arbol Vacio.");
		}
		TNodo<E> padre = checkPosition(p);
		//Si es una hoja
		if(isExternal(p)) {
			throw new InvalidPositionException("Error (addBefore(p,rb,e)) --> La posicion p no tiene hijos");
		}
		TNodo<E> hermanoDerecho = checkPosition(rb);
		TNodo<E> nodo = new TNodo<E>(e,padre);
		try {
			if(!nodo.getPadre().equals(hermanoDerecho.getPadre())) {
				throw new InvalidPositionException("Error (addBefore(p,rb,e)) --> El nuevo nodo , y el nodo derecho, no son hermanos");
			}
			PositionList<TNodo<E>> lista = padre.getHijos();
			Position<TNodo<E>> pos = lista.first();
			while(!pos.element().equals(hermanoDerecho)) {
				pos = lista.next(pos);
			}
			lista.addBefore(pos, nodo);
		}
		catch(EmptyListException | BoundaryViolationException ex) {
			throw new InvalidPositionException("Error (addBefore(p,rb,e))");
		}
		size++;
		return nodo;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> nuevo = null; 
		TNodo<E> nodo = null;
		TNodo<E> hijoDerecho = null;
		PositionList<TNodo<E>> hijos = null;
		Position<TNodo<E>> pp = null;
		boolean encontre = false;

		if (size == 0) {
			throw new InvalidPositionException("Error (addAfter(p,lb,e)) --> Arbol vacio");
		}
		
		nodo = checkPosition(p);
		hijoDerecho = checkPosition(lb);
		nuevo = new TNodo<E>(e, nodo);
		hijos = nodo.getHijos();

		if (nodo.getHijos().isEmpty()) {
			throw new InvalidPositionException("Error (addAfter(p,lb,e)) --> El nodo p no es padre de rb");
		}
		try {
			pp = hijos.first();

			while (pp != null && !encontre) {// busco a lb en la lista de hijos de de p
				if (hijoDerecho.equals(pp.element())) {
					encontre = true;
				} else {
					pp = (!(pp == hijos.last()) ? hijos.next(pp) : null);
				}
			}
			if (!encontre) {
				throw new InvalidPositionException("Error (addAfter(p,lb,e)) --> El nodo p no es padre de rb");
			}

			hijos.addAfter(pp, nuevo);

			size++;
		} catch (EmptyListException | BoundaryViolationException e1) {
			e1.printStackTrace();
		}

		return nuevo;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if(!nodo.getHijos().isEmpty()) {
			throw new InvalidPositionException("Error (removeExternalNode(p)) --> No es un nodo externo.");
		}
		removeNode(nodo);
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if(nodo.getHijos().isEmpty()) {
			throw new InvalidPositionException("Error (removeInternalNode(p)) --> No es un nodo interno.");
		}
		removeNode(nodo);
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {

		if (size == 0) {// Check si el arbol es vacio
			throw new InvalidPositionException("Error (removeNode(p)) --> No se puede eliminar de un arbol vacio");
		}

		TNodo<E> n = checkPosition(p);// Check posicion nodo valida
		try {
			if (n == raiz){// el nodo que se pretende eliminar es la raiz
				if (raiz.getHijos().size() == 1) {// la raiz tiene un solo hijo
					Position<TNodo<E>> rN = raiz.getHijos().first();
					rN.element().setPadre(null);
					raiz.getHijos().remove(rN);
					raiz = rN.element();
				} else { 
					if (size == 1){// el arbol tiene un unico nodo
						raiz = null;
					}else{// se quiere eliminar la raiz pero no es posible por la estructura del arbol
						throw new InvalidPositionException("Error (removeNode(p)) --> Solo se puede eliminar la raiz si es el unico elemento o si tiene un solo hijo");
					}
				}
			}else{// Se quiere eliminar un nodo interno o un nodo hoja.
				TNodo<E> padre = n.getPadre();
				PositionList<TNodo<E>> hPadre = padre.getHijos(); // hijos del padre (hermanos de n)
				PositionList<TNodo<E>> hN = n.getHijos();// hijos de n

				// buscar a n dentro de los hijos del padre
				Position<TNodo<E>> posDeN;
				Position<TNodo<E>> cursor = hPadre.first();
				while (cursor.element() != n && cursor != null) {
					if (cursor == hPadre.last()) {
						cursor = null;
					}else {
						cursor = hPadre.next(cursor);
					}
				}
				if (cursor != null) {
					posDeN = cursor;
				}else {
					throw new InvalidPositionException("Error (removeNode(p)) --> La estructura no corresponde a un arbol valido");
				}

				// si n tiene hijos, se recorren e insertan ordenados en el lugar del padre

				while (!hN.isEmpty()) {
					Position<TNodo<E>> hijoN = hN.first();
					hPadre.addBefore(posDeN, hijoN.element());
					hijoN.element().setPadre(padre);
					hN.remove(hijoN);
				}
				// eliminamos a n de la lista
				hPadre.remove(posDeN);
			}
			// decrementamos el tamaño de la estructura
			size--;
		} catch (EmptyListException | BoundaryViolationException e) {
			throw new InvalidPositionException("Error (removeNode(p)) --> La estructura no corresponde a un arbol valido");
		}
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
